package suicidesquad.primavera;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FactoryTest {

    @Test
    public void factory_returns_new_instance() {
        
        Object result = Factory.getObject(SampleClass.class);

        assertNotNull(result);
    }

    @Test
    public void factory_returns_requested_class() {
        
        Object result = Factory.getObject(SampleClass.class);

        assertEquals(result.getClass(), SampleClass.class);
    }

    @Test
    public void factory_can_instantiate_child_member() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        assertNotNull(result.nestedClassComponent);
    }

    @Test
    public void factory_returns_null_if_child_member_does_not_have_inject() {

        SampleClass result = (SampleClass)Factory.getObject(SampleClass.class);

        assertNull(result.nestedClassNotComponent);
    }

    // TODO: Define exception class in this case, maybe custom?
    @Test(expected = Exception.class)
    public void factory_throws_error_if_member_has_inject_but_class_is_not_component() {

        Factory.getObject(BrokenClass.class);
    }
}