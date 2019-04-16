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
	
	public void addLeaf(Content contentLeaf, Content contentNewChild) {
		Leaf aux = this.searchLeaf(contentLeaf);
		aux.getChilds().add(new Leaf(contentNewChild));
	}
	
	public Leaf searchLeaf(Content content) {
		
		Queue<Leaf> queueAux = new LinkedList<Leaf>();
		queueAux.add(root);
		
		while(queueAux.size() != 0) {
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
			
			this.stack.push(leaf.getContent());
			
			for (Leaf child : leaf.getChilds()) {
				leaf.setContent(this.postOrder(child));
			}
			Content lastLeaf = this.stack.pop();
			lastLeaf.setInstance(leaf.getContent().getNewInstance());
			return lastLeaf;
		} else {
			return this.root.getContent();
		}
	}
	
	public Object getObject(Class<?> classType) throws RuntimeException {

		this.root = new Leaf(new Content(classType, 1));
		
		for (Field field : classType.getDeclaredFields()) {

			Metadata meta = new Metadata(field);

			if (meta.getInjected() && meta.getComponent()) {
				
				//Crear hoja y agregarla al arbol
				
			}
		}
			
			
			

		return this.postOrder(this.root).getInstance();

	}
}
