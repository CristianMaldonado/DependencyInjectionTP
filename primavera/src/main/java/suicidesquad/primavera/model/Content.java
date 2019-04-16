package suicidesquad.primavera.model;

public class Content {

	private int id;
	private Class<?> classType;
	private int quantity;
	private Object instance;
	
	public Content(Class<?> classType, int quantity) {
		this.classType = classType;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	
	
}
