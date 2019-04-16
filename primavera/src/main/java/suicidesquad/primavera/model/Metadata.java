package suicidesquad.primavera.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;

import suicidesquad.primavera.annotations.Component;
import suicidesquad.primavera.annotations.Injected;

public class Metadata {

    public Metadata(Field field) {
        initializeObject(field);
    }

    private boolean collection;
    private boolean injected;
    private int count;
    private Class<?> implementation;
    private boolean component;
    private boolean singleton;
    private Field field;

    private void initializeObject(Field field) {

    	this.field = field;
    	
        Injected injectedAnnotation = field.getAnnotation(Injected.class);

        if (injectedAnnotation == null)
            return;

        this.injected = true;
        this.count = injectedAnnotation.count();
        this.implementation = injectedAnnotation.implementation();
        this.singleton = injectedAnnotation.singleton();

        Class<?> fieldClass = field.getType();

        this.collection = isClassCollection(fieldClass);
        
        if(this.collection) {
        	ParameterizedType listType = (ParameterizedType) field.getGenericType();
        	Class<?> listclass = (Class<?>) listType.getActualTypeArguments()[0];
        	this.component = listclass.getAnnotation(Component.class) != null;
        } else {        	
        	this.component = field.getType().getAnnotation(Component.class) != null;
        }
    }

    public boolean getCollection() {
        return collection;
    }

    public boolean getInjected() {
        return injected;
    }

    public int getCount() {
        return count;
    }

    public Class<?> getImplementation() {
        return implementation;
    }

    public boolean getComponent() {
        return component;
    }

    public boolean getSingleton() {
        return singleton;
    }
    
    public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	private static boolean isClassCollection(Class<?> c) {
        return Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c) || c.isArray();
    }
}
