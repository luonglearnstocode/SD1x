package week2;

/*
 * A Squarelotron consists basically of a matrix of numbers. 
 * This matrix can be decomposed as square rings which can flip independently in two different ways: 
 * Upside-Down and through the Main Diagonal.
 */
public class Squarelotron {
	int[][] squarelotron;
	int size;
	
	/*
	 * Fills the 2-dimensional array with the numbers 1 to n squared, in order. 
	 * Sets the size instance variable to be n.
	 */
	public Squarelotron(int n) {
		this.size = n;
		int value = 1;
		squarelotron = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.squarelotron[i][j] = value;
				value++;
			}
		}
	}
	
	/*
	 * checks whether a square is located inside the provided ring
	 */
	public boolean isOnRing(int i, int j, int ring) {
		boolean isOn = (i == ring - 1 || j == ring - 1 
				|| i == size - ring || j == size - ring); // a square has at least 1 side on the ring
		boolean isOut = (i <= ring - 2 || i >= size + 1 - ring 
				|| j <= ring - 2 || j >= size + 1 - ring); // the square is outside the ring
		return isOn && !isOut; // on or not outside => inside
	}
	
	/*
	 *  performs the Upside-Down Flip of the squarelotron 
	 *  returns the new squarelotron. 
	 *  The original squarelotron should not be modified.
	 */
	public Squarelotron upsideDownFlip(int ring) {
		Squarelotron newSquarelotron = new Squarelotron(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isOnRing(i, j, ring)) {
					newSquarelotron.squarelotron[i][j] = this.squarelotron[size - 1 - i][j]; // flip pattern
				}
			}
		}
		return newSquarelotron;
		
	}
	
	/*
	 *  performs the  Main Diagonal Flip of the squarelotron 
	 *  returns the new squarelotron. 
	 *  The original squarelotron should not be modified.
	 */
	public Squarelotron mainDiagonalFlip(int ring) {
		Squarelotron newSquarelotron = new Squarelotron(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isOnRing(i, j, ring)) {
					newSquarelotron.squarelotron[i][j] = this.squarelotron[j][i]; // flip pattern
				}
			}
		}
		return newSquarelotron;
	}
	
	/*
	 * The argument numberOfTurns indicates the number of times the entire squarelotron should be rotated 90° clockwise. 
	 * Any integer, including zero and negative integers, is allowable as the argument. 
	 * A value of -1 indicates a 90° counterclockwise rotation. 
	 * This method modifies the internal representation of the squarelotron; it does not create a new squarelotron.
	 */
	public void rotateRight(int numberOfTurns) {
		numberOfTurns = (numberOfTurns % 4 + 4) % 4; // take care of negative number of turns
		for (int n = 0; n < numberOfTurns; n++) {
			Squarelotron newSquarelotron = new Squarelotron(size);
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					newSquarelotron.squarelotron[j][size - 1 - i] = squarelotron[i][j]; 
				}
			}
			this.squarelotron = newSquarelotron.squarelotron;
		}
	}

	public static void main(String[] args) {
		int n = 5;
		Squarelotron se = new Squarelotron(n);
		se = se.mainDiagonalFlip(3);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(se.isOnRing(i, j, 3));
//				System.out.print(se.squarelotron[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
