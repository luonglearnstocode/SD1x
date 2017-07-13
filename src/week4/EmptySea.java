package week4;

import java.util.Arrays;

public class EmptySea extends Ship {
	public EmptySea() {
		this.setLength(1);
		this.setHit(new boolean[1]);
		Arrays.fill(this.getHit(), false);
	}
	
	@Override
	public String getShipType() {
		return "empty";
	}
	
	@Override
	public boolean shootAt(int row, int column) {
		this.getHit()[0] = true;
		return false;
	}
	
	@Override
	public boolean isSunk() {
		return false;
	}
	
	@Override
	public String toString() {
		return this.getHit()[0] == true ? "-" : ".";
	}
	
}
