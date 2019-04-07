package suicidesquad.primavera.test;

import org.junit.Assert;
import org.junit.Test;

import suicidesquad.primavera.model.SampleClass;
import suicidesquad.primavera.src.Factory;

public class ListTest {

    @Test
    public void list_is_null_if_not_injected_nor_component() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNull(result.listOfComponentWithFourElements);
    }

    @Test
    public void list_is_null_if_not_injected_and_component() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNull(result.listOfComponentNotInjected);
    }

    @Test(expected = RuntimeException.class)
    public void list_throws_error_if_injected_and_not_component() {
        // TODO: Implementation
    }

    @Test
    public void list_is_not_null_if_injected_and_component_without_count() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNotNull(result.listOfComponentWithFourElements);
        Assert.assertEquals(result.listOfComponentWithFourElements.size(), 0);
    }

    @Test
    public void list_has_multiple_instances_if_injected_and_component_with_count() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNotNull(result.listOfComponentWithFourElements);
        Assert.assertEquals(result.listOfComponentWithFourElements.size(), 4);
    }

    @Test(expected = RuntimeException.class)
    public void list_throws_error_if_injected_and_component_but_implementation_is_not_specified() {
        // TODO: Implementation
    }

    @Test
    public void list_is_not_null_if_injected_and_component_without_count_with_implementation() {
        // TODO: Implementation
    }

    @Test
    public void list_has_multiple_instances_if_injected_and_component_with_count_implementation_is_specified() {
        
        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNotNull(result.listOfComponentWithFourElements);
        Assert.assertEquals(result.listOfComponentWithFourElements.size(), 4);
    }
}
