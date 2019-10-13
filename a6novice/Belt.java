package a6novice;
import comp401.sushi.Plate;

public class Belt  {

	private int size;
	private Plate[] plates;
	
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
		if (position < 0 || position >= size ) {
			throw new IllegalArgumentException();
		}
		return plates[position];
	}
	
	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		if (plate == (null)) {
			throw new IllegalArgumentException();
		}
		if (position < 0 || position >= size ) {
			throw new IllegalArgumentException();
		}
		if (plates[position] != null) {
			throw new BeltPlateException(position, plate, this);
		}
		plates[position] = plate;
	}
	
	public void clearPlateAtPosition(int position) {
		if (position < 0 || position >= size ) {
			throw new IllegalArgumentException();
		}
		plates[position] = null;
	}
	
	public Plate removePlateAtPosition(int position) {
		Plate removedPlate;
		if (position < 0 || position >= size ) {
			throw new IllegalArgumentException();
		}
		if (plates[position].equals(null)) {
			throw new java.util.NoSuchElementException();
		}
		removedPlate = plates[position];
		plates[position] = null;
		return removedPlate;
	}
}
