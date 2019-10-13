package a6adept;

import java.util.Iterator;

import comp401.sushi.Plate;

public class BeltIterator implements Iterator<Plate> {

	private Belt belt;
	private int start_position;
	
	public BeltIterator(Belt belt, int start_position) {
		this.belt = belt;
		
		int newPos;
		if (start_position < 0) {
			newPos = (start_position % belt.getSize()) + belt.getSize();
		} else {
			newPos = start_position % belt.getSize();
		}
		this.start_position = newPos;
	}
	
	@Override
	public boolean hasNext() {
		for (int i  = 0; i < belt.getSize(); i++) {
			if (belt.getPlateAtPosition((start_position + i) % belt.getSize()) != (null)) {
				return true;
			} 
		}
		return false;
	}


	@Override
	public Plate next() {
		Plate nextPlate;
		for (int i  = 1; i < belt.getSize(); i++) {
			if (belt.getPlateAtPosition((start_position + i) % belt.getSize()) != (null)) {
				nextPlate = belt.getPlateAtPosition(start_position + i);
				start_position += i;
				return nextPlate;
			} 
		}
		throw new java.util.NoSuchElementException();
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
