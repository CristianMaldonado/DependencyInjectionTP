package suicidesquad.primavera.model;

import java.util.ArrayList;
import java.util.Stack;

public class Leaf {

	private Content content;
	private ArrayList<Leaf> childs;
	
	public Leaf(Content content) {
		this.content = content;
		this.childs = new ArrayList<Leaf>();
	}

	public void addLeaf(Leaf newLeaf) {
		this.childs.add(newLeaf);
	}
	
	public Content getContent() {
		return content;
	}
	
	public void setContent(Content value) {
		this.content = value;
	}
	
	public ArrayList<Leaf> getChilds() {
		return childs;
	}
	
	public void setChilds(ArrayList<Leaf> childs) {
		this.childs = childs;
	}
	
	public void showTree() {
		
		int nivel = 0;
		Stack<Leaf> stack = new Stack<Leaf>();
		
		stack.push(this);
		
		while(!stack.isEmpty()) {
			Leaf leaf = stack.pop();
			System.out.println("Nivel: " + nivel);
			
			System.out.println("Clase: " + leaf.getContent().getMeta().getFieldClass().toString());
			

			if(!leaf.getChilds().isEmpty()) {
				nivel++;
			}
			
			leaf.getChilds().forEach((child) -> stack.push(child));
		}
	}
	

	@Override
	public String toString() {
		return "Leaf (content=" + content + ", childs=" + childs + ")";
	}
	
	
}
