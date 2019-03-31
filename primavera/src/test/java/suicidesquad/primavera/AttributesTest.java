package suicidesquad.primavera;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class AttributesTest {

    @Test
    public void inject_attribute_present_in_child_members() {
        
    }

    @Test
    public void inject_attribute_error_if_not_present_in_child_members() {
        
        Object result = Factory.getObject(BrokenClass.class);

        assertNull(result);
    }

    @Test
    public void component_attribute_present_in_children() {
        
    }

    @Test
    public void component_attribute_not_present_in_children() {
        
    }
}