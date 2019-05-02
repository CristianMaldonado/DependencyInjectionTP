package suicidesquad.primavera.model;

import suicidesquad.primavera.annotations.Injected;

public class SampleClassSingleton {

	@Injected(singleton=true)
	public NestedClassComponent nestedClassComponent;
	
	@Injected(singleton=true)
	public NestedClassComponent nestedClassComponent2;
	
}
