package suicidesquad.primavera.main;

import suicidesquad.primavera.src.Auto;
import suicidesquad.primavera.src.Factory;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {

		Auto a = (Auto) Factory.getObject(Auto.class);
			
		System.out.println(a.getClass().getName());		
		
	}
}

