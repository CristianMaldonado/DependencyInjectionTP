package suicidesquad.primavera.src;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import suicidesquad.primavera.model.Content;
import suicidesquad.primavera.model.Leaf;
import suicidesquad.primavera.model.Metadata;

public class Factory {
	
	private Leaf root;
	private Stack<Content> stack;
	
	public Factory() {
		this.stack = new Stack<Content>();
	}
	
	
	public void addToSpecificLeaf(Content contentLeaf, Content contentNewChild) {
		Leaf aux = this.searchLeaf(contentLeaf);
		aux.getChilds().add(new Leaf(contentNewChild));
	}
	
	public Leaf searchLeaf(Content content) {
		
		Queue<Leaf> queueAux = new LinkedList<Leaf>();
		queueAux.add(root);
		
		while(!queueAux.isEmpty()) {
			Leaf leaf = queueAux.poll();
			
			if(leaf.compareTo(content) == 0) {
				return leaf;
			} else {
				queueAux.addAll(leaf.getChilds());
			}
		}
		
		return null;
	}

	private Content postOrder(Leaf leaf) {
		
		if(leaf != null) {
			
			if(!leaf.getChilds().isEmpty()) {
				Content c = leaf.getContent();
				this.stack.push(c); 
			}
			
			for (Leaf child : leaf.getChilds()) {
				leaf.setContent(this.postOrder(child));
			}
			
			if(!this.stack.isEmpty()) {				
				Content lastLeafContent = this.stack.pop();
				
				lastLeafContent.setInstance(lastLeafContent.getNewInstance());
				
				try {
					
					lastLeafContent.getMeta().getField().setAccessible(true);
					
					lastLeafContent.getMeta().getField().set(lastLeafContent.getInstance(), leaf.getContent().getNewInstance());
				
				} catch (IllegalArgumentException | IllegalAccessException e) {

					throw new RuntimeException();
				}
				
				
				return lastLeafContent;
			} else {				
				return leaf.getContent();
			}
		} else {
			return this.root.getContent();
		}
	}
	
	public Object getObject(Class<?> classType) {

		this.createTree(classType);
		return this.postOrder(this.root).getNewInstance();

	}
	
	
	public void createTree(Class<?> classType) {
		
		this.root = new Leaf(new Content(classType, null));	
		this.addLeaf(root, classType);
	}

	private void addLeaf(Leaf leaf, Class<?> classType) {
		
		for (Field field : classType.getDeclaredFields()) {

			Metadata meta = new Metadata(field);
		
			if (meta.getInjected() && meta.getComponent()) {
				Leaf newLeaf = new Leaf(new Content(field.getType(), meta));
				leaf.addLeaf(newLeaf);
				this.addLeaf(newLeaf, field.getType());
			}
		}
	}
	
}
