package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public class ArrayObject extends ObjectType {

	public ArrayObject(Field field) {
		super(field);
	}

	@Override
	public Object getInstance() {
		System.out.println("Array Object");
		return null;
	}

	@Override
	public Class<?> getFieldClass() {
		return field.getType().getComponentType();
	}

}
