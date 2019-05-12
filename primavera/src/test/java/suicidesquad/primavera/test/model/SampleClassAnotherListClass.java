package suicidesquad.primavera.test.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import suicidesquad.primavera.annotations.Injected;

public class SampleClassAnotherListClass {

	@Injected(implementation=LinkedList.class)
	public List<NestedClassComponent> linkedListComponent;
	
	@Injected
	public List<NestedClassComponent> defaultListComponent;
	
	@Injected(implementation=ArrayList.class)
	public List<NestedClassComponent> arrayListComponent;
	
}
