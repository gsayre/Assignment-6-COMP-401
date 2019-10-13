package a6jedi;
import java.util.Iterator;

import comp401.sushi.Plate;

public class Belt implements Iterable<Plate> {

	private int size;
	private Plate[] plates;
	private int sizeCount = 0;
	
	public Belt (int size) {
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		this.size = size;
		Plate[] plates = new Plate[size];
		this.plates = plates;
	}
	
	public int getSize() {
		return size;
	}
	
	public Plate getPlateAtPosition(int position) {
		int newPos;
		if (position < 0) {
			newPos = (position % size) + size;
			return plates[newPos];
		} else {
			newPos = position % size;
			return plates[newPos];
		}
	}
	
	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		int newPos;
		if (position < 0) {
			newPos = (position % size) + size;
		} else {
			newPos = position % size;
		}
		
		if (plate == (null)) {
			throw new IllegalArgumentException();
		}
		if (plates[newPos] != null) {
			throw new BeltPlateException(position, plates[newPos], this);
		}
		plates[newPos] = plate;
	}
	
	public void clearPlateAtPosition(int position) {
		int newPos;
		if (position < 0) {
			newPos = (position % size) + size;
		} else {
			newPos = position % size;
		}
		
		plates[newPos] = null;
	}
	
	public Plate removePlateAtPosition(int position) {
		int newPos;
		if (position < 0) {
			newPos = (position % size) + size;
		} else {
			newPos = position % size;
		}
		
		Plate removedPlate;
		if (plates[newPos].equals(null)) {
			throw new java.util.NoSuchElementException();
		}
		removedPlate = plates[position];
		plates[newPos] = null;
		return removedPlate;
	}
	
	public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException {
		int newPos;
		if (position < 0) {
			newPos = (position % size) + size;
		} else {
			newPos = position % size;
		}

		if (plates[newPos] == (null)) {
				plates[newPos] = plate;
				return newPos;
			} else if (sizeCount == size - 1) {
				throw new BeltFullException(this);
			} else {
				sizeCount++;
				setPlateNearestToPosition(plate, position + 1);
			}
		
		return newPos;
	}

	@Override
	public Iterator<Plate> iterator() {
		return new BeltIterator(this, 0);
	}
	
	public Iterator<Plate> iteratorFromPosition(int position) {
		return new BeltIterator(this, position - 1);
	}
	
	public Iterator<Plate> iterator(double max_price) {
		return new PriceThresholdBeltIterator(this, 0, max_price);
	}
	
	public Iterator<Plate> iterator(Plate.Color color){
		return new ColorFilteredBeltIterator(this, 0, color);
	}
	
	public Iterator<Plate> iteratorFromPosition(int position, double max_price){
		return new PriceThresholdBeltIterator(this, position - 1, max_price);
	}
	
	public Iterator<Plate> iteratorFromPosition(int position, Plate.Color color){
		return new ColorFilteredBeltIterator(this, position - 1, color);
	}
	
	public void rotate() {
		Plate[] newPlates = new Plate[size];
		int count = 1;
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				newPlates[0] = plates[i];
			} else {
			newPlates[count] = plates[i];
			count++;
			}
		}
		plates = newPlates;
	}
}
