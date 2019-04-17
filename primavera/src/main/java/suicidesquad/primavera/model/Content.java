package suicidesquad.primavera.model;

public class Content {

	private int id;
	private Class<?> classType;
	private Object instance;
	private String fieldName;
	
	public Content(Class<?> classType, String fieldName) {
		this.classType = classType;
		this.fieldName = fieldName;
		this.id = IdGenerator.getNextId();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", classType=" + classType + ", instance=" + instance + ", fieldName=" + fieldName
				+ "]";
	}
	
}
