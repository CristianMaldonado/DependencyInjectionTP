package suicidesquad.primavera.test;

import org.junit.Assert;
import org.junit.Test;

import suicidesquad.primavera.model.CanNotInstantiateClass;
import suicidesquad.primavera.model.SampleClass;
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

    // TODO: Preguntar si este caso borde debería ser así
    @Test
    public void factory_member_is_null_if_it_is_not_injected_and_is_component() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        Assert.assertNull(result.componentNotInjected);
    }

    @Test(expected = RuntimeException.class)
    public void factory_throws_excepcion_if_can_not_instantiate_object() {

        Factory.getObject(CanNotInstantiateClass.class);
    }
}
