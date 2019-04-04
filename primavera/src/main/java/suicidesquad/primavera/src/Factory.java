package suicidesquad.primavera.src;

import java.lang.reflect.Field;
import suicidesquad.primavera.model.Metadata;

public class Factory {

	public static Object getObject(Class<?> classType) {

		try {

			// TODO: MG: Esto deberíamos hacerlo al final, ahora nos sirve a fines prácticos
			Object instance = classType.newInstance();

			for (Field field : classType.getDeclaredFields()) {

				Metadata meta = new Metadata(field);

				if (meta.getInjected() && meta.getComponent()) {

					field.setAccessible(true);
					field.set(instance, Factory.getObject(field.getType()));
				}
			}

			return instance;

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
