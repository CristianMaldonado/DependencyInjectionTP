package suicidesquad.primavera.main;

import suicidesquad.primavera.src.Auto;
import suicidesquad.primavera.src.Factory;

public class App {

	public static void main(String[] args) {

		Auto a = new Factory().getObject(Auto.class);

		System.out.println(a);
	}
}
