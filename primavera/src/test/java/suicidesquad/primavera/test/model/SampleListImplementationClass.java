package suicidesquad.primavera.test.model;

import java.util.List;

import suicidesquad.primavera.annotations.Injected;

public class SampleListImplementationClass {

    @Injected(implementation = FirstSample.class)
    public List<Sample> listOfFirstSampleWithoutCount;
}
