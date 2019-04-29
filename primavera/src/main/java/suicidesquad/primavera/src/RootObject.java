package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public class RootObject  extends ObjectType<Object> {

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

	@Override
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) {
		return leaf.getInstance();
	}

}
