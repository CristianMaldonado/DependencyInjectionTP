package suicidesquad.primavera.model;

import suicidesquad.primavera.annotations.Injected;

public class BrokenClassImplementationWithObject {

	@Injected(implementation=FirstSample.class)
	public NestedClassComponent nestedClassComponent;
}
