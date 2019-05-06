package suicidesquad.primavera.test.model;

import java.util.List;
import suicidesquad.primavera.annotations.Injected;

public class BrokenClassImplementationList {

    @Injected(count=4)
    public List<Sample> listOfSampleWithoutImplementation;    
}
