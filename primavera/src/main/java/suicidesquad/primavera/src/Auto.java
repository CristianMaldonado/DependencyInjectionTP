package suicidesquad.primavera.src;

import suicidesquad.primavera.annotations.Injected;

public class Auto {

	@Injected
	private Rueda rueda;

	public Rueda getRueda() {
		return this.rueda;
	}

	@Override
	public String toString() {
		return "Auto [ruedas=" + rueda + "]";
	}
}
