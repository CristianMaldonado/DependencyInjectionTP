package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public class InterfaceObject extends DecoratorObject {

	public InterfaceObject(ObjectType<?> objectType) {
		super(objectType);
	}

	@Override
	public Object getInstance() {
		try {
			return getFieldClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Class<?> getFieldClass() {
		return fieldMetadata.getImplementation();
	}

	@Override
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {
		return super.createInstance(field, leaf, lastInstance);
	}	

}
