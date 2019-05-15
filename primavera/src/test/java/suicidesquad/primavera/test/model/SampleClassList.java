package suicidesquad.primavera.test.model;

import java.util.List;
import suicidesquad.primavera.annotations.Injected;

public class SampleClassList {
    
    public List<String> listOfNotComponent;

    public List<NestedClassComponent> listOfComponentNotInjected;

    @Injected
    public List<NestedClassComponent> listOfComponentEmpty;

    @Injected(count=4)
    public List<NestedClassComponent> listOfComponentWithFourElements;
}
