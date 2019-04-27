package suicidesquad.primavera.src;

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
        return array || list;
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
    
    public void createObject(Leaf leaf) {
    	
		for (Field field : getFieldClass().getDeclaredFields()) {

			if (field.getName().equals(leaf.getMetadata().getName())) {
				try {

					field.setAccessible(true);
					
					if(leaf.getMetadata().isCollection()) { 
					
						List<Object> newList = new ArrayList<Object>();
						
						Collection<?> collection = (Collection<?>) field.get(getInstance());

						if(collection != null) newList.addAll(collection);
						newList.add(leaf.getInstance());
						field.set(instance, newList);
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
