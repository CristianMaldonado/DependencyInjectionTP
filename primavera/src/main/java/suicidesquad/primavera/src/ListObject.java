package suicidesquad.primavera.src;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListObject extends ObjectType<List<Object>> {

	public ListObject(Metadata meta) {
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
	public Class<?> getFieldClass() {//TODO Hay que ver el caso por defecto: List es interfaz y puede colocarse implementation para el list, ej: LinkedList, por defecto es ArrayList
        ParameterizedType listType = (ParameterizedType) fieldMetadata.getField().getGenericType();
        
//        System.out.println("Tipo lista: -> " + fieldMetadata.getField());
        
        Class<?> className = (Class<?>) listType.getActualTypeArguments()[0];
        
//        System.out.println("Clase de la lista: -> " + className);
//        
//        if(className.isInterface()) {
//        	//Tengo que retornar la clase de la implementacion
//        	System.out.println("Clase : -> " + className);
//        }
        return className;
	}

	@Override
	public List<Object> createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {

		//Si tiene implementation || la clase es una interfaz y solo hay una clase que la implementa => Tengo que usar la clase de la Subclase que implementa la interfaz
				
		List<Object> newList = new ArrayList<Object>();
		
		Collection<?> collection = (Collection<?>) field.get(lastInstance);

		if(collection != null) newList.addAll(collection);
		newList.add(leaf.getInstance());
		return newList;
	}

}
