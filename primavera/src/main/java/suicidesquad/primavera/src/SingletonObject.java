package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public class SingletonObject extends DecoratorObject {//TODO Ahora Singleton se aplica sobre @Component ya no se aplica en @Injected

	private static Object singletonInstance = null;
	
	public SingletonObject(ObjectType<?> type) {
		super(type);
	}
	
	public Object getInstance() {
		
		if(singletonInstance == null) {
			
			singletonInstance = super.getInstance();
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
