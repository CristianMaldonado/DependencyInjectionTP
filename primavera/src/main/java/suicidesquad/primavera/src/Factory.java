package suicidesquad.primavera.src;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import suicidesquad.primavera.model.Content;
import suicidesquad.primavera.model.Leaf;
import suicidesquad.primavera.model.Metadata;

public class Factory {
	
	@SuppressWarnings("unchecked")
	public static <T> T getObject(Class<?> classType) {

		Leaf root = createTree(classType);
		Stack<Content> stack = new Stack<Content>();;

//		root.showTree();
		
		postOrder(root, stack);
		return (T) (root.getContent()).getInstance();
	}

	private static void postOrder(Leaf leaf, Stack<Content> stack) {

		if (leaf != null) {

			leaf.getContent().newInstance();

			for (Leaf child : leaf.getChilds()) {

				stack.push(leaf.getContent());
				postOrder(child, stack);
			}

			if (!stack.isEmpty()) {

				Content lastLeafContent = stack.pop();
				
				Object childInstance = leaf.getContent().getInstance();

				for (Field field : lastLeafContent.getMeta().getFieldClass().getDeclaredFields()) {

					if (field.getName().equals(leaf.getContent().getMeta().getName())) {
						try {
							
							Object actualInstance = lastLeafContent.getInstance();
							
							field.setAccessible(true);
							
							if(leaf.getContent().getMeta().isCollection()) { 
												
								List<Object> newList = new ArrayList<Object>();
								
								Collection<?> collection = (Collection<?>) field.get(lastLeafContent.getInstance());
								
								if(collection != null) newList.addAll(collection);
								newList.add(childInstance);
								field.set(actualInstance, newList);
							} else {								
								field.set(actualInstance, childInstance);
							}
							

						} catch (IllegalArgumentException | IllegalAccessException e) {
							throw new RuntimeException();
						}
					}
				}

			}
		}
	}
	
	public static Leaf createTree(Class<?> classType) {

		Leaf root = new Leaf(new Content(new Metadata(classType)));
		addLeaf(root, classType);

		return root;
	}

	private static void addLeaf(Leaf leaf, Class<?> classType) {

		for (Field field : classType.getDeclaredFields()) {

			Metadata meta = new Metadata(field);

			if (meta.isInjected() && meta.isComponent()) {
							
				for (int i = 0; i < meta.getCount(); i++) {

					Leaf newLeaf = new Leaf(new Content(meta));
					leaf.addLeaf(newLeaf);
					
					addLeaf(newLeaf, meta.getFieldClass());
				}
			}
		}
	}

}
