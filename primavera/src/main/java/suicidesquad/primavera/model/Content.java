package suicidesquad.primavera.model;

public class Content {

	private int id;
	private Class<?> classType;
	private Metadata meta;
	private Object instance;
	
	public Content(Class<?> classType, Metadata meta) {
		this.classType = classType;
		this.meta = meta;
		this.id = IdGenerator.getNextId();
	}
	
	public Object getNewInstance() {
		try {
			return this.classType.newInstance();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Metadata getMeta() {
		return meta;
	}

	public void setMeta(Metadata meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", classType=" + classType + ", meta=" + meta + ", instance=" + instance + "]";
	}

	
	
}
