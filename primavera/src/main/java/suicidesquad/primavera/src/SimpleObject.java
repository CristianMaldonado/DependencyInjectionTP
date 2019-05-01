package suicidesquad.primavera.src;

import java.lang.reflect.Field;

public class SimpleObject extends ObjectType<Object> {
	
	public SimpleObject(Metadata meta) {
		super(meta);
	}	
	
	@Override
	public Class<?> getFieldClass() {
		return fieldMetadata.getField().getType();
	}

	@Override
	public Object getInstance() {
		try {
			if(fieldMetadata.getCount() > 1) {
				throw new RuntimeException();
			}
			return getFieldClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {
		return  leaf.getInstance();
	}

}
