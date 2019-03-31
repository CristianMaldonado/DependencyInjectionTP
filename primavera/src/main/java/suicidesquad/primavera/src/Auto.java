package suicidesquad.primavera.src;

import suicidesquad.primavera.annotations.Component;
import suicidesquad.primavera.annotations.Injected;

@Component
public class Auto {

	@Injected
	private int ruedas; // TODO en este caso deberia ser una clase

	public int getRuedas() {
		return ruedas;
	}

	public void setRuedas(int ruedas) {
		this.ruedas = ruedas;
	}
	
	
	
}
