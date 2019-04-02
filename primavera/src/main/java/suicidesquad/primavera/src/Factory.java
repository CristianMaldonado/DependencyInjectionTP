package suicidesquad.primavera.src;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Factory {

    public static Object getObject(Class<?> classType) {

        try {

        	//Creo la instancia de la clase
        	Object instance = classType.newInstance();

        	//Obtengo el annotation "Component"
//        	Annotation annotation = classType.getAnnotation(Component.class);

			//Obtengo los atributos publicos y privados de la clase
        	for (Field field : classType.getDeclaredFields()) {
				//Obtengo los annotatiosn de los atributos
				for (Annotation annotation : field.getDeclaredAnnotations()) {

					String annotationName = annotation.annotationType().getSimpleName();
					
					//Si tiene @Injected
					if(annotationName.equals("Injected")) {
						
						//Le doy acceso para modificar el atributo ya que es privado
						field.setAccessible(true);
						
						//Seteo una nueva instancia que se genera de forma recursiva
						field.set(instance, Factory.getObject(field.getType()));
					}
					
//					for (Method methods : annotation.annotationType().getMethods()) {
//						
//					//Los metodos que me devuelve son las variables del annotation ej. "Count"
//						System.out.println("Ann methods: " + methods.getName()); 
//					}
				}
			}
        	return instance;
        	
        	
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
    }
}