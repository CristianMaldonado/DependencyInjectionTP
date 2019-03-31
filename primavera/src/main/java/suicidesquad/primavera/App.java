package suicidesquad.primavera;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {
	
		Auto a = (Auto) Factory.getObject(Auto.class); //Para probar el factory
		
		System.out.println(a.getClass().getName());
		
	}
}
