package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public abstract class DecoratorObject extends ObjectType<Object> {

	private final ObjectType<?> type;
	
	public DecoratorObject(ObjectType<?> type) {
		super(type.fieldMetadata);
		this.type = type;
	}
	
	public Object getInstance() {
		return type.getInstance();
	}
	public Class<?> getFieldClass() {
		return type.getFieldClass();
	}
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {
		return type.createInstance(field, leaf, lastInstance); 
	}
	
}
