package suicidesquad.primavera.test;

import org.junit.Assert;
import org.junit.Test;

import suicidesquad.primavera.model.BrokenClassImplementation;
import suicidesquad.primavera.model.BrokenClassWithCount;
import suicidesquad.primavera.model.CanNotInstantiateClass;
import suicidesquad.primavera.model.FirstSample;
import suicidesquad.primavera.model.NestedClassComponent;
import suicidesquad.primavera.model.OneImplementationClass;
import suicidesquad.primavera.model.SampleClass;
import suicidesquad.primavera.model.SampleClassImplementation;
import suicidesquad.primavera.src.Factory;

public class FactoryTest {

    @Test
    public void factoryReturnsNewInstance() {

        SampleClass result = Factory.getObject(SampleClass.class);

        Assert.assertNotNull(result);
    }

    @Test
    public void factoryReturnsRequestedClass() {
        
        SampleClass result = Factory.getObject(SampleClass.class);

        Assert.assertEquals(SampleClass.class, result.getClass());
    }

    @Test
    public void factoryCreateAnInstanceOfAnAtribute() {

        SampleClass result = Factory.getObject(SampleClass.class);

        Assert.assertNotNull(result.component);
    }
    
    @Test
    public void factoryCreateAnInstanceOfAnAtributeAndThisIsOfTheRequiredClass() {
    	
    	SampleClass result = Factory.getObject(SampleClass.class);
    	
    	Assert.assertEquals(NestedClassComponent.class, result.component.getClass());
    }
    
    @Test
    public void factoryDoesNotCreateAnInstanceOfAnAtributeThatIsOfTypeInterface() {
    	
    	SampleClass result = Factory.getObject(SampleClass.class);
    	
    	Assert.assertNull(result.interfaceComponent);
    }
        
    @Test
    public void factoryCreateAnInstanceOfAnAtributeThatIsAnInterfaceTypeAndHasImplementation() {
    	
    	SampleClass result = Factory.getObject(SampleClass.class);
    	
    	Assert.assertNotNull(result.interfaceComponentWithImplementation);
    	Assert.assertEquals(FirstSample.class, result.interfaceComponentWithImplementation.getClass());
    }

    @Test
    public void factoryCreateAnInstanceOfAnAtributeAndThisIsSingleton() {
    	// el atributo es un objeto y tiene singleton -> es unico
    }
    
    @Test
    public void factoryCreateAnInstanceOfAnAtributeAndThisIsSingletonAndThisIsSingletonThenItIsUnique() {
    	// el atributo es un objeto y tiene singleton -> no tiene otra referencia igual
    }
    
    @Test
    public void factoryDontCreateAnInstanceOfAnAtributeBecauseItIsAnInterfaceTypeAndHasComponent() {

    	BrokenClassImplementation result = Factory.getObject(BrokenClassImplementation.class);
    	
    	Assert.assertNull(result.interfaceWithComponent);
    }
 
    @Test
    public void factoryDontCreateAnInstanceOfAnAtributeBecauseItIsNotAnInterfaceHasImplementationAndSingleton() {
    	// el atributo es objeto pero tiene implementation y tiene singleton -> null
    }
    
    @Test
    public void factoryCreateAnInstanceOfAnAtributeAndItIsUniqueBecauseItIsInterfaceHasImplementationAndSingleton() {
    	// el atributo es de tipo interfaz pero tiene implementation y tiene singleton -> objeto unico, no existe otra referencia
    }
    
    @Test
    public void factoryCreateAnInstanceOfAnAtributeThatIsOfTypeInterfaceWithAnImplementationWithoutSpecifyingImplementation() {
    	// el atributo es de tipo interfaz con una implementacion sin especificar implementation -> tiene que retornar el objeto
    	SampleClassImplementation result = Factory.getObject(SampleClassImplementation.class);
    	
    	Assert.assertNotNull(result.interfaceWithOneImplementation);
    	Assert.assertEquals(OneImplementationClass.class, result.interfaceWithOneImplementation.getClass());
    }
    
    @Test
    public void factoryMemberIsNullIfItIsNotInjectedNorComponent() {

        SampleClass result = Factory.getObject(SampleClass.class);

        Assert.assertNull(result.notComponent);
    }

    @Test
    public void factoryMemberIsNullIfItIsNotInjectedAndIsComponent() {

        SampleClass result = Factory.getObject(SampleClass.class);

        Assert.assertNull(result.componentNotInjected);
    }

    @Test(expected = RuntimeException.class)
    public void factoryThrowsExcepcionIfCanNotInstantiateObject() {

    	Factory.getObject(CanNotInstantiateClass.class);
    }
    
    @Test(expected=RuntimeException.class)
    public void factoryThrowsExceptionIfTheAtributeHasInjectedAndCount() {

    	Factory.getObject(BrokenClassWithCount.class);
    }
    
    @Test(expected=RuntimeException.class)
    public void factoryThrowsExceptionIfTheAtributeHasInjectedCountAndSingleton() {
    	// el atributo es objeto pero tiene count y tiene singleton -> null
    	throw new RuntimeException();
    }
    
    @Test(expected=RuntimeException.class)
    public void factoryThrowsExceptionIfTheAttributeIsAnObjectAndHasImplementation() {
    	// el atributo es un objeto y tiene implementation -> arroja excepcion (lo supongo!)
    	throw new RuntimeException();
    }
    
    @Test(expected=RuntimeException.class)
    public void factoryThrowsExceptionIfTheInterfaceHasMoreThanOneImplementationAndTheImplementationIsNotSpecified() {
    	// el atributo es de tipo interfaz con mas de una implementacion pero no se especifico implementation -> arrija excepcion indicando que hay que especificar la implementacion
    	throw new RuntimeException();
    }
}
