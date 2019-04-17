package suicidesquad.primavera.model;

import suicidesquad.primavera.annotations.Injected;

public class SampleClassArray {
    
    @Injected
    public NestedClassComponent[] arrayOfComponentEmpty;

    @Injected(count=8)
    public NestedClassComponent[] arrayOfComponentWithEightElements;
}
