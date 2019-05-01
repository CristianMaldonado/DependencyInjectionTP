package suicidesquad.primavera.model;

import suicidesquad.primavera.annotations.Component;
import suicidesquad.primavera.annotations.Injected;

@Component
public class Rueda {
	
	@Injected
	private Tornillo torillo;

	@Override
	public String toString() {
		return "Rueda {torillo=" + torillo + "}";
	}



}
