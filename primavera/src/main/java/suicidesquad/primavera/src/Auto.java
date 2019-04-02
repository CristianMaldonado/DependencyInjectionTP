package suicidesquad.primavera.src;

import suicidesquad.primavera.annotations.Injected;

public class Auto {

	@Injected
	private Rueda ruedas;
	
	public Rueda getRuedas() {
		return this.ruedas;
	}

	@Override
	public String toString() {
		return "Auto [ruedas=" + ruedas + "]";
	}
	
	
}
