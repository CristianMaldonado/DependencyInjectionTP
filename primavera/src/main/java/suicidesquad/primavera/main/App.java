package suicidesquad.primavera.main;

import suicidesquad.primavera.model.Auto;
import suicidesquad.primavera.src.Factory;

public class App {

	public static void main(String[] args) {

		Auto a = Factory.getObject(Auto.class);

		System.out.println(a);
	}
}
