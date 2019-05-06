package suicidesquad.primavera.test;

import org.junit.Assert;
import org.junit.Test;

import suicidesquad.primavera.src.Factory;
import suicidesquad.primavera.test.model.*;

public class ListTest {

    @Test
    public void list_is_null_if_not_injected_nor_component() {

        SampleClassList result = Factory.getObject(SampleClassList.class);

        Assert.assertEquals(4, result.listOfComponentWithFourElements.size());
    }

    @Test
    public void list_is_null_if_not_injected_and_component() {

        SampleClassList result = Factory.getObject(SampleClassList.class);

        Assert.assertNull(result.listOfComponentNotInjected);
    }

    @Test(expected = RuntimeException.class)
    public void list_throws_error_if_injected_and_not_component() {

        BrokenClass result = Factory.getObject(BrokenClassList.class);
        
        Assert.assertNull(result);
    }

    @Test
    public void list_is_not_null_if_injected_and_component_without_count() {

        SampleClassList result = Factory.getObject(SampleClassList.class);

        Assert.assertNotNull(result.listOfComponentWithFourElements);
    }

    @Test
    public void list_has_multiple_instances_if_injected_and_component_with_count() {

        SampleClassList result = Factory.getObject(SampleClassList.class);

        Assert.assertNotNull(result.listOfComponentWithFourElements);
        Assert.assertEquals(result.listOfComponentWithFourElements.size(), 4);
    }

    @Test
    public void list_is_null_if_injected_and_component_but_implementation_is_not_specified() {
        
        BrokenClassImplementationList broken = Factory.getObject(BrokenClassImplementationList.class);
        
        Assert.assertNull(broken.listOfSampleWithoutImplementation);
//        Assert.fail("Library should have thrown an exception, because Sample is an interface and no concrete implementation is specified.");
    }

    @Test
    public void list_is_not_null_if_injected_and_component_without_count_with_implementation() {

        SampleClassList result = Factory.getObject(SampleClassList.class);
        
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.listOfFirstSampleWithoutCount);
        Assert.assertTrue("Sequence should contain one element.", result.listOfFirstSampleWithoutCount.size() == 1);
    }

    @Test
    public void list_has_multiple_instances_if_injected_and_component_with_count_implementation_is_specified() {
        
        SampleClassList result = Factory.getObject(SampleClassList.class);

        Assert.assertNotNull(result.listOfComponentWithFourElements);
        Assert.assertEquals(result.listOfComponentWithFourElements.size(), 4);
    }
}
