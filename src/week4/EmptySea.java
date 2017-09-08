package week4;

import java.util.Arrays;

/*
 * The Ocean contains a Ship array, every location of which is, or can be, a reference to some Ship. 
 * If a particular location is empty, the obvious thing to do is to put a null in that location. 
 * But this obvious approach has the problem that, every time we look at some location in the array, we have to check if it is null.
 * By putting a non-null value in empty locations, denoting the absence of a ship, we can save all that null checking.
 */
public class EmptySea extends Ship {
	/*
	 * This constructor sets the inherited length variable to 1.
	 */
	public EmptySea() {
		this.setLength(1);
		this.setHit(new boolean[1]);
		Arrays.fill(this.getHit(), false);
	}
	
	@Override
	public String getShipType() {
		return "empty";
	}
	
	/*
	 * This method overrides shootAt(int row, int column) that is inherited from Ship, 
	 * and always returns false to indicate that nothing was hit.
	 */
	@Override
	public boolean shootAt(int row, int column) {
		this.getHit()[0] = true;
		return false;
	}
	
	/*
	 * always returns false to indicate that you didn’t sink anything.
	 */
	@Override
	public boolean isSunk() {
		return false;
	}
	
	/*
	 * Returns a single-character String to use in the Ocean’s print method.
	 */
	@Override
	public String toString() {
		return this.getHit()[0] == true ? "-" : ".";
	}
	
}
