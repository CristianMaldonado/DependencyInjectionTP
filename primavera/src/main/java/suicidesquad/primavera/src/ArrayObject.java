package suicidesquad.primavera.src;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ArrayObject extends ObjectType<Object[]> {

	public ArrayObject(Metadata meta) {
		super(meta);
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
		return fieldMetadata.getField().getType().getComponentType();
	}

	@Override
	public Object[] createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {
		
		Object[] oldArray = (Object[]) field.get(lastInstance);
		
		int oldSize = (oldArray != null) ? oldArray.length : 0;
		
		Object[] newArray = (Object[]) Array.newInstance(leaf.getMetadata().getFieldClass(), oldSize + 1);
								
		if(oldArray != null) {
			
			for (int i = 0; i < oldArray.length; i++) {
				Array.set(newArray, i, oldArray[i]);
			}
		}
		
		newArray[oldSize] = leaf.getInstance();
		return newArray;
	}

}
