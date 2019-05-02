package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public class SingletonObject extends DecoratorObject {

	private static Object singletonInstance = null;
	
	public SingletonObject(ObjectType<?> type) {
		super(type);
	}
	
	public Object getInstance() {
		if(singletonInstance == null) {			
			try {
				if(fieldMetadata.getCount() > 1) {
					throw new RuntimeException();
				}
				singletonInstance = getFieldClass().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException();
			}
		}
		return singletonInstance;
	}
	public Class<?> getFieldClass() {
		return super.getFieldClass();
	}
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {
		return super.createInstance(field, leaf, lastInstance); 
	}

}
