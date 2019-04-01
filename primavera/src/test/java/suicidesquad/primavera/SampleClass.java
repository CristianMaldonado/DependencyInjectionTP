package suicidesquad.primavera;

import java.util.List;
import suicidesquad.primavera.annotations.Injected;

public class SampleClass {
    
    @Injected
    public NestedClassComponent component;

    public NestedClassComponent componentNotInjected;

    @Injected(count=4)
    public List<NestedClassComponent> listOfComponent;

    public NestedClassNotComponent notComponent;
}
