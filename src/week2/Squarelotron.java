package week2;

public class Squarelotron {
	int[][] squarelotron;
	int size;
	
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
	
	public boolean isOnRing(int i, int j, int ring) {
		boolean isOn = (i == ring - 1 || j == ring - 1 
				|| i == size - ring || j == size - ring);
		boolean isOut = (i <= ring - 2 || i >= size + 1 - ring 
				|| j <= ring - 2 || j >= size + 1 - ring);
		return isOn && !isOut;
	}
	
	public Squarelotron upsideDownFlip(int ring) {
		Squarelotron newSquarelotron = new Squarelotron(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isOnRing(i, j, ring)) {
					newSquarelotron.squarelotron[i][j] = this.squarelotron[size - 1 - i][j];
				}
			}
		}
		return newSquarelotron;
		
	}
	
	public Squarelotron mainDiagonalFlip(int ring) {
		Squarelotron newSquarelotron = new Squarelotron(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isOnRing(i, j, ring)) {
					newSquarelotron.squarelotron[i][j] = this.squarelotron[j][i];
				}
			}
		}
		return newSquarelotron;
	}
	
	public void rotateRight(int numberOfTurns) {
		numberOfTurns = (numberOfTurns % 4 + 4) % 4;
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
