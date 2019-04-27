package suicidesquad.primavera.src;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class ListObject extends ObjectType {

	public ListObject(Field field) {
		super(field);
	}

	@Override
	public Object getInstance() {
		try {
			return getFieldClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException();
		}
	}
	
	@Override
	public Class<?> getFieldClass() {
        ParameterizedType listType = (ParameterizedType) this.field.getGenericType();
        return (Class<?>) listType.getActualTypeArguments()[0];
	}

}
