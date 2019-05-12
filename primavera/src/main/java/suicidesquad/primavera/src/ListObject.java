package suicidesquad.primavera.src;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListObject extends ObjectType<List<Object>> {

	private Class<?> listClass;
	
	public ListObject(Metadata meta) {
		super(meta);
		listClass = ArrayList.class;
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
        ParameterizedType listType = (ParameterizedType) fieldMetadata.getField().getGenericType();
        
        Class<?> className = (Class<?>) listType.getActualTypeArguments()[0];

        return className;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {

		List<Object> newList = null;		
		
		if(!leaf.getMetadata().getImplementation().equals(Object.class)) {
		
			listClass = leaf.getMetadata().getImplementation();
		}
		
		try {
			newList = (List<Object>) listClass.newInstance();
			
		} catch (InstantiationException e) {
			throw new RuntimeException();
		}
		
		Collection<?> collection = (Collection<?>) field.get(lastInstance);

		if(collection != null) newList.addAll(collection);
		newList.add(leaf.getInstance());
		return newList;
	}
	
	
	
//	divide y conquista
	
//	ej: quicksort -> disminuye el volumen de los datos, para bajar la complejidad del ordenamiento, 
//	otra estrategia: algoritmos voraces solucion es un conjunto de valores  -> cumlen con : 
//	- funcion de seleccion 
//	- funcion de factibilidad
//	- funcion solucion
//	- funcuin que busca optimizar el resultado

//programacion dinamica -> pongo en memoria valores ya calculados
//	ej de parcial -> nos da una matriz y me pide el recorrido que haria el algoritmo en la matriz booleanda (haciendo el camino con una flecha)
}



