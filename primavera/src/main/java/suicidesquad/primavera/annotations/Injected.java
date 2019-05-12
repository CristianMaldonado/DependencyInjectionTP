package suicidesquad.primavera.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retention me indica que el annotation se va a ejecutar en tiempo de ejecucion, tiene sentido ya que se utilizara con reflection
 * Target me indica en que parte del codigo puedo usar el annotation, en este caso en un atributo 
 * @author Cristian
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Injected {

	int count() default 1;
	Class<?> implementation() default Object.class;
}
