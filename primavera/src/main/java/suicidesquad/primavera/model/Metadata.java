package suicidesquad.primavera.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;

import suicidesquad.primavera.annotations.Component;
import suicidesquad.primavera.annotations.Injected;

public class Metadata {

    public Metadata(Field field) {

        this.field = field;
        initializeObject(field);
    }

    private String name;
    private Field field;
    private boolean list;
    private boolean array;
    private boolean injected;
    private int count;
    private Class<?> implementation;
    private boolean component;
    private boolean singleton;

    private void initializeObject(Field field) {

        Injected injectedAnnotation = field.getAnnotation(Injected.class);

        if (injectedAnnotation == null)
            return;

        this.injected = true;
        this.count = injectedAnnotation.count();
        this.implementation = injectedAnnotation.implementation();
        this.singleton = injectedAnnotation.singleton();

        Class<?> fieldClass = field.getType();

        this.list = isClassCollection(fieldClass);
        this.array = fieldClass.isArray();

        Class<?> type = field.getType();

        if (this.list) {
            ParameterizedType listType = (ParameterizedType) field.getGenericType();
            type = (Class<?>) listType.getActualTypeArguments()[0];
        } else if (this.array) {
            type = field.getType().getComponentType();
        }

        this.component = type.getAnnotation(Component.class) != null;
    }

    public boolean getCollection() {
        return array || list;
    }

    public String getName() {
        return this.field.getName();
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

    private static boolean isClassCollection(Class<?> c) {
        return Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c);
    }
}
