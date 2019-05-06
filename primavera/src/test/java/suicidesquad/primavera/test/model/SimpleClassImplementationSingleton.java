package suicidesquad.primavera.test.model;

import suicidesquad.primavera.annotations.Injected;

public class SimpleClassImplementationSingleton {
	
	@Injected(implementation=FirstSample.class, singleton=true)
	public Sample sampleClassSingleton;
	
	@Injected(implementation=FirstSample.class, singleton=true)
	public Sample sampleClassSingleton2;
}
