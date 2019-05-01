package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public abstract class ObjectType<T> {

	protected Metadata fieldMetadata;
	
	public ObjectType() { }
	
	public ObjectType(Metadata meta) {
		this.fieldMetadata = meta;
	}
	
	public abstract Object getInstance();
	public abstract Class<?> getFieldClass();
	public abstract T createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException;
}
