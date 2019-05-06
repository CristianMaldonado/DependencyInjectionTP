package suicidesquad.primavera.test.model;

import suicidesquad.primavera.annotations.Injected;

public class BrokenClassSingleton {

	@Injected(count=2, singleton=true)
	public NestedClassComponent nestedClassComponent;
}
