package suicidesquad.primavera.src;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class InterfaceObject extends DecoratorObject {

	public InterfaceObject(ObjectType<?> objectType) {
		super(objectType);
	}

	@Override
	public Object getInstance() {
		try {
			return getFieldClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Class<?> getFieldClass() {
		
		Class<?> interfaceClass = super.getFieldClass();
		if(interfaceClass.isArray()) {
			return interfaceClass;
		}
		
		int implementationsCount = 0;
		Class<?> classWithOneImplementation = fieldMetadata.getImplementation();
		
		try {
			Class<?>[] classesInThePackage = getClasses(interfaceClass.getPackage().getName());
			Class<?> classThatImplementsTheInterface = Object.class;
			
			for (Class<?> classType : classesInThePackage) {
				
				if(!classType.isInterface()) {
					
					for(Class<?> interfaz : classType.getInterfaces()) {
						if(interfaz.getName().equals(interfaceClass.getName())) {
							implementationsCount++;
							classThatImplementsTheInterface = classType;
						}
					}
				}
			}
			
			if(implementationsCount == 1) {
				classWithOneImplementation = classThatImplementsTheInterface;
			}
			
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException();
		}

		return classWithOneImplementation; 
	}

	@Override
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {
		return super.createInstance(field, leaf, lastInstance);
	}
	
	private Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile()));
	    }
	    ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
	    for (File directory : dirs) {
	        classes.addAll(findClasses(directory, packageName));
	    }
	    return classes.toArray(new Class[classes.size()]);
	}

	
	private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
	    List<Class<?>> classes = new ArrayList<Class<?>>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            assert !file.getName().contains(".");
	            classes.addAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
	        }
	    }
	    return classes;
	}

}
