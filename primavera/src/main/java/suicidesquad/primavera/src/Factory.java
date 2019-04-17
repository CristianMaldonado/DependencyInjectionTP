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

	private void postOrder(Leaf leaf) {
		
		if(leaf != null) {
			
			for (Leaf child : leaf.getChilds()) {
				
				this.stack.push(leaf.getContent());
				
				this.postOrder(child);
			}
			
			if(!this.stack.isEmpty()) {
				Content lastLeafContent = this.stack.pop();
				
				lastLeafContent.newInstance(); //Crea tu instancia
				leaf.getContent().newInstance();// Crea la instancia del hijo
				Object childInstance = leaf.getContent().getInstance(); // Dame la instancia del hijo

				//Asigna la instancia del hijo al atributo del padre
				
				for (Field field : lastLeafContent.getClassType().getDeclaredFields()) {
					if(field.getName().equals(leaf.getContent().getFieldName())) {
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
	
	public Object getObject(Class<?> classType) {

		this.createTree(classType);
		this.postOrder(root);
		return root.getContent().getInstance();

	}
	
	
	public void createTree(Class<?> classType) {
		
		this.root = new Leaf(new Content(classType, "Root"));	
		this.addLeaf(root, classType);
	}

	private void addLeaf(Leaf leaf, Class<?> classType) {
		
		for (Field field : classType.getDeclaredFields()) {

			Metadata meta = new Metadata(field);
		
			if (meta.getInjected() && meta.getComponent()) { //Si el atributo tiene Injected y la clase del atributo tiene Component
				Leaf newLeaf = new Leaf(new Content(field.getType(), field.getName())); // Instancio nueva Hoja pasando la clase del atributo 
				leaf.addLeaf(newLeaf); // Agrego la hoja como hijo de la hoja anterior
				this.addLeaf(newLeaf, field.getType()); // Llamo de nuevo con el hijo recien agregado
			}
		}
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
	
}
