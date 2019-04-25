package suicidesquad.primavera.model;

import java.lang.reflect.Field;

public abstract class ObjectType {

	protected Field field;
	
	public ObjectType(Field field) {
		this.field = field;
	}
	
	public abstract Object getInstance();
	public abstract Class<?> getFieldClass();
}
