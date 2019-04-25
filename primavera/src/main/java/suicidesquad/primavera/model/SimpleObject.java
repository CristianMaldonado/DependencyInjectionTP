package suicidesquad.primavera.model;

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
		System.out.println("Simple Object");
		return null;
	}


}
