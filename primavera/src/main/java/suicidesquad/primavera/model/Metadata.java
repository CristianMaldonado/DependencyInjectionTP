package suicidesquad.primavera.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import suicidesquad.primavera.annotations.Component;
import suicidesquad.primavera.annotations.Injected;

public class Metadata {

	public Metadata(Class<?> fieldType) { 
		this.fieldClass = fieldType; 
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
    private Class<?> fieldClass;
    private Class<?> implementation;
    private ObjectType objectType;
    
    private void initializeObject(Field field) {

        Injected injectedAnnotation = field.getAnnotation(Injected.class);

        if (injectedAnnotation == null)
            return;

        this.injected = true;
        this.count = injectedAnnotation.count();
        this.implementation = injectedAnnotation.implementation();
        this.singleton = injectedAnnotation.singleton();

        this.list = isClassCollection(field.getType());
        this.array = field.getType().isArray();

        this.objectType = new SimpleObject(field);

        if (this.list) {
            this.objectType = new ListObject(field);
        } else if (this.array) {
            this.objectType = new ArrayObject(field);
        }

        this.fieldClass = this.objectType.getFieldClass();
        this.component = fieldClass.getAnnotation(Component.class) != null;
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
    
    public Class<?> getFieldClass() {
    	return this.fieldClass;
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


    private static boolean isClassCollection(Class<?> c) {
        return Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c);
    }
}
