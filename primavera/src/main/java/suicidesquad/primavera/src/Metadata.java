package suicidesquad.primavera.src;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import suicidesquad.primavera.annotations.Component;
import suicidesquad.primavera.annotations.Injected;

public class Metadata {

	public Metadata(Class<?> rootClass) { 
		objectType = new RootObject(rootClass);
	}
	
    public Metadata(Field field) {

        this.field = field;
        initializeObject(field);
    }

    private Field field;
    private boolean list;
    private boolean array;
    private boolean injected;
    private int count;
    private boolean component;
    private boolean singleton;
//    private Class<?> rootClass;
    private Class<?> implementation;
    private ObjectType objectType;
    
    private Object instance;
    
    private void initializeObject(Field field) {

        Injected injectedAnnotation = field.getAnnotation(Injected.class);

        if (injectedAnnotation == null)
            return;

        injected = true;
        count = injectedAnnotation.count();
        implementation = injectedAnnotation.implementation();
        singleton = injectedAnnotation.singleton();

        list = isClassCollection(field.getType());
        array = field.getType().isArray();

        objectType = new SimpleObject(field);

        if (list) {
            objectType = new ListObject(field);
        } else if (array) {
            objectType = new ArrayObject(field);
        }

        component = objectType.getFieldClass().getAnnotation(Component.class) != null;
    }

    public boolean isCollection() {
        return list;
    }
    
    public boolean isArray() {
    	return array;
    }

    public String getName() {
        return this.field.getName();
    }

    public boolean isInjected() {
        return injected;
    }
  
    public int getCount() {
        return count;
    }

    public Class<?> getImplementation() {
        return implementation;
    }

    public boolean isComponent() {
        return component;
    }

    public boolean isSingleton() {
        return singleton;
    }

	public Object getInstance() {
    	return instance;
    }
    
    public Class<?> getFieldClass() {
    	return objectType.getFieldClass();
    }

    public void newInstance() {
    	instance = objectType.getInstance();
    }
    
    public void assignObject(Leaf leaf) {
    	
		for (Field field : getFieldClass().getDeclaredFields()) {

			if (field.getName().equals(leaf.getMetadata().getName())) {
				try {

					field.setAccessible(true);
					
//					Object o = objectType.createInstance(field, leaf, instance);
//					Esto rompo porque el createInstance retorna un Object pero para arrays necesito Object[]  y para listas List<Object> 
//					field.set(instance, o);
					
					if(leaf.getMetadata().isCollection()) { //TODO Delegar la responsabilidad de la contruccion de los objetos a cada subclase del strategy
					
						List<Object> newList = new ArrayList<Object>();
						
						Collection<?> collection = (Collection<?>) field.get(instance);

						if(collection != null) newList.addAll(collection);
						newList.add(leaf.getInstance());
						field.set(instance, newList);
					} else if(leaf.getMetadata().isArray()) {
						
						Object[] oldArray = (Object[]) field.get(instance);
						
						int oldSize = (oldArray != null) ? oldArray.length : 0;
						
						Object[] newArray = (Object[]) Array.newInstance(leaf.getMetadata().getFieldClass(), oldSize + 1);
												
						if(oldArray != null) {
							
							for (int i = 0; i < oldArray.length; i++) {
								Array.set(newArray, i, oldArray[i]);
							}
						}
						
						newArray[oldSize] = leaf.getInstance();
						
						field.set(instance, newArray);
					} else {						
						field.set(instance, leaf.getInstance());
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException();
				}
			}
		}
    }
    
    private static boolean isClassCollection(Class<?> c) {
        return Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c);
    }
}
