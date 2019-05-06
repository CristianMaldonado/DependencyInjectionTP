package suicidesquad.primavera.test.model;

import suicidesquad.primavera.annotations.Injected;

public class SampleClassArray {
    
    @Injected
    public NestedClassComponent[] arrayOfComponentEmpty;

    @Injected(count=8)
    public NestedClassComponent[] arrayOfComponentWithEightElements;
}
