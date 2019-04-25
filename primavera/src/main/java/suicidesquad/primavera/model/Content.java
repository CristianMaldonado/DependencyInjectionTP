package suicidesquad.primavera.model;

public class Content {

	private Object instance;
	private Metadata meta;
	
	public Content(Metadata meta) {
		this.meta = meta;
	}
	
	public void newInstance() {
		try {
			this.instance = this.meta.getFieldClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}
	
	public void createObject() {
		
	}


	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public Metadata getMeta() {
		return meta;
	}

	public void setMeta(Metadata meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "Content [instance=" + instance + ", meta=" + meta + "]";
	}
	

}
