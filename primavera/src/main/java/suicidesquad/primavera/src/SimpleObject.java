package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public class SimpleObject extends ObjectType {
	
	public SimpleObject(Field field) {
		super(field);
	}	
	
	@Override
	public Class<?> getFieldClass() {
		return this.field.getType();
	}

	@Override
	public Object getInstance() {
		try {
			return getFieldClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}


}
