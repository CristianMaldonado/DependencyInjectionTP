package suicidesquad.primavera.src;


import java.lang.annotation.Annotation;

import suicidesquad.primavera.annotations.Component;

public class Factory {

    public static Object getObject(Class<?> classType) {

        try {
 
        	//Obtengo el annotation "Component"
        	Annotation annotation = classType.getAnnotation(Component.class);
        	
        	if(annotation != null) {
        		return classType.newInstance();	
        	} else {
        		// No tiene sentido pero por el momento lo dejo asi, tenemos que ver este tema, si se debe arrojar una exception
        		// o retonar algo distinto
        		return null;
        	}
        				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
    }
}