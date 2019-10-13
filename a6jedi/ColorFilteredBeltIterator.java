package a6jedi;

import java.util.Iterator;

import comp401.sushi.Plate;

public class ColorFilteredBeltIterator implements Iterator<Plate> {

	private Belt belt;
	private int start_position;
	private Plate.Color color_filter;
	private boolean nextDone = false;
	
	public ColorFilteredBeltIterator(Belt belt, int start_position, Plate.Color color_filter ) {
		this.belt = belt;
		
		int newPos;
		if (start_position < 0) {
			newPos = (start_position % belt.getSize()) + belt.getSize();
		} else {
			newPos = start_position % belt.getSize();
		}
		this.start_position = newPos;
		this.color_filter = color_filter;
	}
	
	@Override
	public boolean hasNext() {
		for (int i  = 0; i < belt.getSize(); i++) {
			if (belt.getPlateAtPosition((start_position + i) % belt.getSize()) != (null)) {
				if (belt.getPlateAtPosition((start_position + i) % belt.getSize()).getColor().equals(color_filter)) {
					return true;
				}	
			} 
		}
		return false;
	}


	@Override
	public Plate next() {
		nextDone = true;
		Plate nextPlate;
		for (int i  = 1; i < belt.getSize(); i++) {
			if (belt.getPlateAtPosition((start_position + i) % belt.getSize()) != (null)) {
				if (belt.getPlateAtPosition((start_position + i) % belt.getSize()).getColor().equals(color_filter)) {
				nextPlate = belt.getPlateAtPosition(start_position + i);
				start_position += i;
				System.out.println(nextPlate);
				return nextPlate;
				}
			} 
		}
		throw new java.util.NoSuchElementException();
	}
	
	public void remove() {
		if (nextDone) {
			belt.removePlateAtPosition(start_position);
			nextDone = false;
		} else {
			throw new IllegalStateException();
		}
	}

}
