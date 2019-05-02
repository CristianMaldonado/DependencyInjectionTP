package suicidesquad.primavera.model;

import suicidesquad.primavera.annotations.Injected;

public class BrokenClassSingleton {

	@Injected(count=2, singleton=true)
	public NestedClassComponent nestedClassComponent;
}
