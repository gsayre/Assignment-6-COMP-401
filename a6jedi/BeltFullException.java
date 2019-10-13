package a6jedi;

public class BeltFullException extends Throwable {

	private Belt belt;
	
	public BeltFullException(Belt belt) {
		this.belt = belt;
	}
	
	public Belt getBelt() {
		return belt;
	}
}
