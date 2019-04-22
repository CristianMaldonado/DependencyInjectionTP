package suicidesquad.primavera.src;

import java.util.ArrayList;

import suicidesquad.primavera.annotations.Injected;

public class Auto {

	@Injected
	private Rueda rueda;
	
	@Injected(count=2)
	private ArrayList<Puerta> puertas;

	public Rueda getRueda() {
		return this.rueda;
	}

	@Override
	public String toString() {
		return "Auto [rueda=" + rueda + ", puerta=" + puertas + "]";
	}



}
