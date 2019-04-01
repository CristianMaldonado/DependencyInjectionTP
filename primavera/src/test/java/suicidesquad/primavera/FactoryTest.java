package suicidesquad.primavera;

import org.junit.Assert;
import org.junit.Test;
import suicidesquad.primavera.src.Factory;

public class FactoryTest {

    @Test
    public void factory_returns_new_instance() {

        Object result = Factory.getObject(SampleClass.class);

        Assert.assertNotNull(result);
    }

    @Test
    public void factory_returns_requested_class() {
        
        Object result = Factory.getObject(SampleClass.class);

        Assert.assertEquals(result.getClass(), SampleClass.class);
    }

    @Test
    public void factory_can_instantiate_child_member() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNotNull(result.component);
    }

    // TODO: Me gustaría preguntar si este caso borde debería ser así
    @Test
    public void factory_member_is_null_if_it_is_not_injected_nor_component() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNull(result.notComponent);
    }

    // TODO: Me gustaría preguntar si este caso borde debería ser así
    @Test
    public void factory_member_is_null_if_it_is_not_injected_and_is_component() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNull(result.componentNotInjected);
    }

    @Test
    public void factory_member_list_has_multiple_instances_with_injected_count() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNotNull(result.listOfComponent);
        Assert.assertEquals(result.listOfComponent.size(), 4);
    }

    // TODO: Define exception class in this case, maybe custom?
    @Test(expected = Exception.class)
    public void factory_throws_error_if_member_is_injected_but_not_component() {

        Factory.getObject(BrokenClass.class);
    }
}
