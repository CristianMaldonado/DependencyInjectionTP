package suicidesquad.primavera.test.model;

import suicidesquad.primavera.annotations.Injected;

public class SampleClass {
    
    @Injected
    public NestedClassComponent component;

    public NestedClassComponent componentNotInjected;

    public NestedClassNotComponent notComponent;
    
    @Injected
    public Sample interfaceComponent;
    
    @Injected(implementation=FirstSample.class)
    public Sample interfaceComponentWithImplementation;
}
