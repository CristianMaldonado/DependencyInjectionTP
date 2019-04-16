package suicidesquad.primavera.model;

import java.util.ArrayList;

public class Leaf implements Comparable<Content>{

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

	@Override
	public int compareTo(Content o) {
		return this.content.getId() - o.getId();
	}

	@Override
	public String toString() {
		return "Leaf (content=" + content + ", childs=" + childs + ")";
	}
	
	
}
