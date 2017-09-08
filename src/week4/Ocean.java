package week4;

import java.util.Random;

public class Ocean {
	private Ship[][] ships = new Ship[20][20]; // Used to quickly determine which ship is in any given location.
	private int shotsFired; // The total number of shots fired by the user.
	private int hitCount; // The number of times a shot hit a ship. If the user shoots the same part of a ship more than once, every hit is counted, even though the additional ”hits” don’t do the user any good.
	private int shipsSunk; // The number of ships sunk. Remember that you have a total of 13 ships.       
	
	/*
	 * Creates an ”empty” ocean (fills the ships array with a bunch of EmptySea instances).
	 * Also initializes any game variables, such as how many shots have been fired.
	 */
	public Ocean() {
		shotsFired = 0; hitCount = 0; shipsSunk = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				EmptySea emptySea = new EmptySea();
				emptySea.placeShipAt(i, j, true, this);
			}
		}
	}
	
	/*
	 * Place all randomly on the (initially empty) ocean. 
	 * Place larger ships before smaller ones, or you may end up with no legal place to put a large ship. 
	 */
	public void placeAllShipsRandomly() {
		Random random = new Random();
//		random.setSeed(10);
		Ship[] ships = new Ship[13];
		for (int i = 0; i < 13; i++) {
			if (i == 0) {
				ships[i] = new BattleShip();
			} else if (i == 1) {
				ships[i] = new BattleCruiser();
			} else if (i < 4) {
				ships[i] = new Cruiser();
			} else if (i < 6) {
				ships[i] = new LightCruiser();
			} else if (i < 9) {
				ships[i] = new Destroyer();
			} else if (i < 13) {
				ships[i] = new Submarine();
			}
		}
		
		for (Ship ship : ships) {
			while (true) {
				int row = random.nextInt(20);
				int column = random.nextInt(20);
				boolean horizontal = random.nextBoolean();
//				System.out.println(ship.getShipType() + " "+ row + " " + column + " horizontal? " + horizontal + " ok? "+ ship.okToPlaceShipAt(row, column, horizontal, this));
				if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
					ship.placeShipAt(row, column, horizontal, this);
					break;
				}
			}
		}
	}
	
	/*
	 * Returns true if the given location contains a ship, false if it does not.
	 */
	public boolean isOccupied(int row, int column) {
		return !ships[row][column].getShipType().equals("empty");
	}
	
	/*
	 * Returns true if the given location contains a ”real” ship, still afloat, (not an EmptySea), false if it does not. 
	 * In addition, this method updates the number of shots that have been fired, and the number of hits. 
	 * Note: If a location contains a ”real” ship, shootAt should return true every time the user shoots at that same location. 
	 * Once a ship has been ”sunk”, additional shots at its location should return false.
	 */
	public boolean shootAt(int row, int column) {
		this.shotsFired++;
		if (isOccupied(row, column)) {
			if (ships[row][column].shootAt(row, column)) {
				if (ships[row][column].isSunk()) {
					System.out.println("You just sunk a " + ships[row][column].getShipType());
					shipsSunk++;
				}
				hitCount++;
				return true;
			}
			return false;
		} else {
			ships[row][column].shootAt(row, column);
		}
		return false;
	}
	
	/*
	 * Prints the ocean. To aid the user, row numbers should be displayed along the left edge of the array, and column numbers should be displayed along the top. 
	 * Numbers should be 00 to 19, not 1 to 20.
	 * The top left corner square should be 0, 0.
	 * Use ’S’ to indicate a location that you have fired upon and hit a (real) ship, 
	 * ’-’ to indicate a location that you have fired upon and found nothing there, 
	 * ’x’ to indicate a location containing a sunken ship,
	 * and ’.’ (a period) to indicate a location that you have never fired upon.
	 */
	public void print() {
		System.out.println(toString());
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int i = 0; i < 20; i++) {
			sb.append(String.format("%3d", i));
		}
		sb.append("\n");
		
		for (int i = 0; i < 20; i++) {
			sb.append(String.format("%2d ", i));
			for (int j = 0; j < 20; j++) {
//				sb.append(ships[i][j].toString());
				
				if (!ships[i][j].wasShootAt(i, j)) { // never been fired
					sb.append(".");
				} else {
					sb.append(ships[i][j].toString());
//					if (!isOccupied(i, j)) { // fired, but nothing there
//						sb.append("-");
//					} else {
//						sb.append(ships[i][j].toString());
//					}
				}
				

				
				sb.append("  ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/*
	 * Returns the 20x20 array of ships. 
	 * The methods in the Ship class that take an Ocean parameter really need to be able to look at the contents of this array; 
	 * the placeShipAt method even needs to modify it. 
	 */
	public Ship[][] getShipArray() {
		return ships;
	}

	public void setShips(Ship[][] ships) {
		this.ships = ships;
	}
	
	/*
	 * Returns the number of shots fired (in this game).
	 */
	public int getShotsFired() {
		return shotsFired;
	}

	/*
	 * Returns the number of hits recorded (in this game). All hits are counted, not just the first time a given square is hit.
	 */
	public int getHitCount() {
		return hitCount;
	}

	/*
	 * Returns the number of ships sunk (in this game).
	 */
	public int getShipsSunk() {
		return shipsSunk;
	}
	
	/*
	 * Returns true if all ships have been sunk, otherwise false.
	 */
	public boolean isGameOver() {
		return shipsSunk == 13;
	}
}
