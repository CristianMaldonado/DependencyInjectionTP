package suicidesquad.primavera.src;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	@Override
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {

		List<Object> newList = new ArrayList<Object>();
		
		Collection<?> collection = (Collection<?>) field.get(lastInstance);

		if(collection != null) newList.addAll(collection);
		newList.add(leaf.getInstance());
		return newList;
	}

}
