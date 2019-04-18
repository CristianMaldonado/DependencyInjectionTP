package suicidesquad.primavera.src;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import suicidesquad.primavera.model.Content;
import suicidesquad.primavera.model.Leaf;
import suicidesquad.primavera.model.Metadata;

public class Factory {

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

				for (Field field : lastLeafContent.getClassType().getDeclaredFields()) {
					if (field.getName().equals(leaf.getContent().getFieldName())) {
						try {
							field.setAccessible(true);
							field.set(lastLeafContent.getInstance(), childInstance);

						} catch (IllegalArgumentException | IllegalAccessException e) {
							throw new RuntimeException();
						}
					}
				}

			}
		}
	}

	public static <T> T getObject(Class<?> classType) {

		Leaf root = createTree(classType);
		Stack<Content> stack = new Stack<Content>();;

		postOrder(root, stack);
		return (T) (root.getContent()).getInstance();
	}

	public static Leaf createTree(Class<?> classType) {

		Leaf root = new Leaf(new Content(classType, "Root"));
		addLeaf(root, classType);

		return root;
	}

	private static void addLeaf(Leaf leaf, Class<?> classType) {

		for (Field field : classType.getDeclaredFields()) {

			Metadata meta = new Metadata(field);

			if (meta.getInjected() && meta.getComponent()) {
			
				for (int i = 0; i < meta.getCount(); i++) {

					Leaf newLeaf = new Leaf(new Content(field.getType(), field.getName()));
					leaf.addLeaf(newLeaf);
					
					addLeaf(newLeaf, field.getType());
				}
			}
		}
	}

	public void addToSpecificLeaf(Content contentLeaf, Content contentNewChild, Leaf root) {
		Leaf aux = this.searchLeaf(contentLeaf, root);
		aux.getChilds().add(new Leaf(contentNewChild));
	}

	public Leaf searchLeaf(Content content, Leaf root) {

		Queue<Leaf> queueAux = new LinkedList<Leaf>();
		queueAux.add(root);

		while (!queueAux.isEmpty()) {
			Leaf leaf = queueAux.poll();

			if (leaf.compareTo(content) == 0) {
				return leaf;
			} else {
				queueAux.addAll(leaf.getChilds());
			}
		}

		return null;
	}

}
