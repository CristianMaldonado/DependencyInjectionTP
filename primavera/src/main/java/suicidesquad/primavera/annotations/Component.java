package suicidesquad.primavera.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {

	//TODO por el momento deberia ir vacio, ya que solo me sirve para saber que clases se inyectan

}
