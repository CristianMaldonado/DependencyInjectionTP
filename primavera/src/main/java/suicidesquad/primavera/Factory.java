package suicidesquad.primavera;

public class Factory {

    public static Object getObject(Class<?> classType) {

        try {
			
        	return classType.newInstance();
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
    }
}