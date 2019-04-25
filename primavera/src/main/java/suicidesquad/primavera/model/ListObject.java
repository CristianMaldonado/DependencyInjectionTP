package suicidesquad.primavera.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class ListObject extends ObjectType {

	public ListObject(Field field) {
		super(field);
	}

	@Override
	public Object getInstance() {
		System.out.println("List Object");
		return null;
	}
	
	@Override
	public Class<?> getFieldClass() {
        ParameterizedType listType = (ParameterizedType) this.field.getGenericType();
        return (Class<?>) listType.getActualTypeArguments()[0];
	}

}
