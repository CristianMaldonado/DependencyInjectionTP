package suicidesquad.primavera.src;

import java.lang.reflect.Field;
import java.util.Stack;

public class Factory {
	
	@SuppressWarnings("unchecked")
	public static <T> T getObject(Class<?> classType) {

		Leaf root = createTree(classType);
		Stack<Metadata> stack = new Stack<Metadata>();;

//		root.showTree();
		
		postOrder(root, stack);
		return (T) (root).getInstance();
	}

	private static void postOrder(Leaf leaf, Stack<Metadata> stack) {

		if (leaf != null) {

			leaf.getMetadata().newInstance();

			for (Leaf child : leaf.getChilds()) {

				stack.push(leaf.getMetadata());
				postOrder(child, stack);
			}

			if (!stack.isEmpty()) {

				Metadata lastLeafMeta = stack.pop();
				
				lastLeafMeta.createObject(leaf);

			}
		}
	}
	
	public static Leaf createTree(Class<?> classType) {

		Leaf root = new Leaf(new Metadata(classType));
		addLeaf(root, classType);

		return root;
	}

	private static void addLeaf(Leaf leaf, Class<?> classType) {

		for (Field field : classType.getDeclaredFields()) {

			Metadata meta = new Metadata(field);

			if (meta.isInjected() && meta.isComponent()) {
							
				for (int i = 0; i < meta.getCount(); i++) {

					Leaf newLeaf = new Leaf(meta);
					leaf.addLeaf(newLeaf);
					
					addLeaf(newLeaf, meta.getFieldClass());
				}
			}
		}
	}

}
