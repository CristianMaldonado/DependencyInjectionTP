package suicidesquad.primavera.src;

public class RootObject  extends ObjectType {

	private Class<?> rootClass;
	
	public RootObject(Class<?> rootClass) {
		this.rootClass = rootClass;
	}
	
	@Override
	public Object getInstance() {
		try {
			return rootClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Class<?> getFieldClass() {
		return rootClass;
	}

}
