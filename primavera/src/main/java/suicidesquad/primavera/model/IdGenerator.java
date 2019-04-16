package suicidesquad.primavera.model;

public class IdGenerator {

	private static int id = 1;
	
	private IdGenerator() {  }
	
	public static int getNextId() {
		int next = IdGenerator.id;
		IdGenerator.id++;
		return next;
	}
}
