package suicidesquad.primavera.src;

import java.util.ArrayList;
import java.util.Stack;

public class Leaf {

	private Metadata metadata;
	private ArrayList<Leaf> childs;
	
	public Leaf(Metadata meta) {
		metadata = meta;
		childs = new ArrayList<Leaf>();
	}

	public void addLeaf(Leaf newLeaf) {
		childs.add(newLeaf);
	}
	
	public Metadata getMetadata() {
		return metadata;
	}

	public ArrayList<Leaf> getChilds() {
		return childs;
	}
	
	public Object getInstance() {
		return metadata.getInstance();
	}
	
	public void showTree() {
		
		int nivel = 0;
		Stack<Leaf> stack = new Stack<Leaf>();
		
		stack.push(this);
		
		while(!stack.isEmpty()) {
			Leaf leaf = stack.pop();
			System.out.println("Nivel: " + nivel);
			
			System.out.println("Clase: " + leaf.getMetadata());
			

			if(!leaf.getChilds().isEmpty()) {
				nivel++;
			}
			
			leaf.getChilds().forEach((child) -> stack.push(child));
		}
	}

	@Override
	public String toString() {
		return "Leaf {metadata=" + metadata + ", childs=" + childs + "}";
	}
	
}
