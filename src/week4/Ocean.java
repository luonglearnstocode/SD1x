package week4;

import java.util.Random;

public class Ocean {
	private Ship[][] ships = new Ship[20][20];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	public Ocean() {
		shotsFired = 0; hitCount = 0; shipsSunk = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				EmptySea emptySea = new EmptySea();
				emptySea.placeShipAt(i, j, true, this);
			}
		}
	}
	
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
	
	public boolean isOccupied(int row, int column) {
		return !ships[row][column].getShipType().equals("empty");
	}
	
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
					if (!isOccupied(i, j)) { // fired, but nothing there
						sb.append("-");
					} else {
						sb.append(ships[i][j].toString());
					}
				}
				sb.append("  ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public Ship[][] getShipArray() {
		return ships;
	}

	public void setShips(Ship[][] ships) {
		this.ships = ships;
	}

	public int getShotsFired() {
		return shotsFired;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getShipsSunk() {
		return shipsSunk;
	}
	
	public boolean isGameOver() {
		return shipsSunk == 13;
	}
}
