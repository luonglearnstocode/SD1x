package week4;

public abstract class Ship {
	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean horizontal;
	boolean[] hit;
	
	public abstract String getShipType();
	
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
//		System.out.println("ship at " + row + " " + column  + " " + horizontal);
		if (horizontal) {
			if (column + getLength() > 20) {
				return false;
			}
			for (int i = row - 1; i <= row + 1; i++) {
				for (int j = column - 1; j < column + getLength() + 1; j++) {
					try {
//						System.out.println(i + " " + j + " " + ocean.getShipArray()[i][j].getShipType().equals("empty"));
						if (!ocean.getShipArray()[i][j].getShipType().equals("empty")) {
							return false;
						}
					} catch (Exception e) {
						continue;
					}
				}
			}
		} else {
			if (row + getLength() > 20) {
				return false;
			}
			for (int i = row - 1; i < row + getLength() + 1; i++) {
				for (int j = column - 1; j <= column + 1; j++) {
					try {
//						System.out.println(i + " " + j + " " + ocean.getShipArray()[i][j].getShipType().equals("empty"));
						if (!ocean.getShipArray()[i][j].getShipType().equals("empty")) {
							return false;
						}
					} catch (Exception e) {
						continue;
					}
				}
			}
		}
//		System.out.println("");
		return true;
	}
	
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		bowRow = row;
		bowColumn = column;
		this.horizontal = horizontal;
		
		/*
		 * Set references to the ship in the ships array in the Ocean object
		 */
		if (horizontal) {
			for (int j = column; j < column + getLength(); j++) {
				ocean.getShipArray()[row][j] = this;
			}
		} else {
			for (int i = row; i < row + getLength(); i++) {
				ocean.getShipArray()[i][column] = this;
			}
		}
	}
	
	public boolean wasShootAt(int row, int column) {
		if (horizontal) {
			return hit[column - this.bowColumn] == true;
		} else {
			return hit[row - this.bowRow] == true;
		}
	}
	
	public boolean shootAt(int row, int column) {
		if (!isSunk()) {
			if (horizontal) {
				if (row == this.bowRow && column < this.bowColumn + length) {
					hit[column - this.bowColumn] = true;
					return true;
				} 
			} else {
				if (column == this.bowColumn && row < this.bowRow + length) {
					hit[row - this.bowRow] = true;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isSunk() {
		for (boolean b : hit) if (!b) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return isSunk() ? "x" : "S";
	}
	
	public int getBowRow() {
		return bowRow;
	}
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	public int getBowColumn() {
		return bowColumn;
	}
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public boolean isHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	public boolean[] getHit() {
		return hit;
	}
	public void setHit(boolean[] hit) {
		this.hit = hit;
	}
	
	
}
