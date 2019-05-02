package suicidesquad.primavera.src;

import java.lang.reflect.Field;

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
	public Class<?> getFieldClass() {//TODO cuando la interfaz tiene una sola implementacion -> getImplementation esta vacio -> Tengo que fijarme la clase que lo implementa
		
//		Class<?> fieldClass = fieldMetadata.getImplementation();
		
//		Class<?> interfaceClass = super.getFieldClass();
//		Class<?>[] implementations = fieldClass.getInterfaces();
//		boolean classImplementsTheInterface = false; 
//		for(int i = 0; i < implementations.length; i++) {
//			if(implementations[i] == interfaceClass) classImplementsTheInterface = true;
//		}
		
		if(fieldMetadata.getImplementation() == null) {
			throw new RuntimeException();
		}
		return fieldMetadata.getImplementation(); 
	}

	@Override
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {
		return super.createInstance(field, leaf, lastInstance);
	}	

}
