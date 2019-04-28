package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public abstract class ObjectType {

	protected Field field;
	
	public ObjectType() { }
	
	public ObjectType(Field field) {
		this.field = field;
	}
	
	public abstract Object getInstance();
	public abstract Class<?> getFieldClass();
	public abstract Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException;
}
