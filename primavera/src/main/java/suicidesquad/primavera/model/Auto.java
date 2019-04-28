package suicidesquad.primavera.model;

import java.util.ArrayList;
import java.util.Arrays;

import suicidesquad.primavera.annotations.Injected;

public class Auto {

	@Injected
	private Motor motor;
	
	@Injected(count=4)
	private Rueda[] ruedas;
	
	@Injected(count=2)
	private ArrayList<Butaca> butacas;

	@Injected(implementation=AutoestereoSonyImple.class)
	private Autoestereo autoestereo;

	@Override
	public String toString() {
		return "{Auto: motor=" + motor + ", ruedas=" + Arrays.toString(ruedas) + ", butacas=" + butacas + ", autoestereo="
				+ autoestereo + "}";
	}

}
