package suicidesquad.primavera.model;

public class Content {

	private Class<?> classType;
	private Object instance;
	private Metadata meta;
	
	public Content(Metadata meta) {
		this.classType = meta.getFieldType();
		this.meta = meta;
	}
	
	public void newInstance() {
		try {
			this.instance = this.classType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	public Class<?> getClassType() {
		return classType;
	}

	public void setClassType(Class<?> classType) {
		this.classType = classType;
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
		return "Content [classType=" + classType + ", instance=" + instance + ", meta=" + meta + "]";
	}
	

}
