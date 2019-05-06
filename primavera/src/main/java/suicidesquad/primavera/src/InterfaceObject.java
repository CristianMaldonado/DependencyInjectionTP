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
	public Class<?> getFieldClass() {//TODO cuando la interfaz tiene una sola implementacion -> getImplementation esta vacio -> Tengo que fijarme la clase que lo implementa
		
//		Class<?> fieldClass = fieldMetadata.getImplementation(); // Cuando no esta viene por defecto Object.class (No me sirve)
		
		Class<?> interfaceClass = super.getFieldClass();
		
		int contadorImplementaciones = 0;
		Class<?> claseConUnaImplementacion = Object.class;
		
		try {
			Class<?>[] clases = getClasses(interfaceClass.getPackage().getName());
						
			for (Class<?> clase : clases) {
				
				if(!clase.isInterface()) {
					
					for(Class<?> interfaz : clase.getInterfaces()) {
						if(interfaz.getName().equals(interfaceClass.getName())) {
							contadorImplementaciones++;
							System.out.println("Contador => " + contadorImplementaciones);
							System.out.println("Clase Concreta => " + clase.getName());
							claseConUnaImplementacion = clase;
						}
					}
				}
				
				
				
//				System.out.println("Clase->" + clase);
			}
			
			if(contadorImplementaciones == 1) {
				return claseConUnaImplementacion;
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
//		Class<?>[] implementations = fieldClass.getInterfaces();
//		boolean classImplementsTheInterface = false; 
//		for(int i = 0; i < implementations.length; i++) {
//			if(implementations[i] == interfaceClass) classImplementsTheInterface = true;
//		}
		
		if(fieldMetadata.getImplementation() == null) {
			throw new RuntimeException();
		}
		return fieldMetadata.getImplementation(); 
	}

	@Override
	public Object createInstance(Field field, Leaf leaf, Object lastInstance) throws IllegalArgumentException, IllegalAccessException {
		return super.createInstance(field, leaf, lastInstance);
	}
	
	
	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
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

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
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
