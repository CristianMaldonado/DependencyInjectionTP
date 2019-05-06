package suicidesquad.primavera.test.model;

import suicidesquad.primavera.annotations.Injected;

public class BrokenClassImplementationWithObject {

	@Injected(implementation=FirstSample.class)
	public NestedClassComponent nestedClassComponent;
}
