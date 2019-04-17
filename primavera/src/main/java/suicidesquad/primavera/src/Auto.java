package suicidesquad.primavera.src;

import suicidesquad.primavera.annotations.Injected;

public class Auto {

	@Injected
	private Rueda rueda;
	
	@Injected
	private Puerta puerta;

	public Rueda getRueda() {
		return this.rueda;
	}

	@Override
	public String toString() {
		return "Auto [rueda=" + rueda + ", puerta=" + puerta + "]";
	}



}
