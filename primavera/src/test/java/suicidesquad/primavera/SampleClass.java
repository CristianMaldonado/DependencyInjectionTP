package suicidesquad.primavera;

import java.util.List;
import suicidesquad.primavera.annotations.Injected;

public class SampleClass {
    
    @Injected
    public NestedClassComponent component;

    public NestedClassComponent componentNotInjected;

    public List<String> listOfNotComponent;

    public List<NestedClassComponent> listOfComponentNotInjected;

    @Injected
    public List<NestedClassComponent> listOfComponentEmpty;

    @Injected(count=4)
    public List<NestedClassComponent> listOfComponentWithFourElements;

    @Injected
    public NestedClassComponent[] arrayOfComponentEmpty;

    @Injected(count=8)
    public NestedClassComponent[] arrayOfComponentWithEightElements;

    public NestedClassNotComponent notComponent;
}
