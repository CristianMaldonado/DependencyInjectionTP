package suicidesquad.primavera.src;

import java.lang.reflect.Field;

import suicidesquad.primavera.model.AutoestereoSonyImple;

public class InterfaceObject extends DecoratorObject {

	public InterfaceObject(ObjectType<?> objectType) {
		super(objectType);
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
	public Class<?> getFieldClass() { //TODO retornar la instancia de la implementation
		
		System.out.println("FIeld " + field);
		
		return AutoestereoSonyImple.class;
	}

	@Override
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {
		return super.createInstance(field, leaf, lastInstance);
	}	

}
