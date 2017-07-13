package week4;

import java.util.Arrays;

public class Submarine extends Ship {
	public Submarine() {
		this.setLength(3);
		this.setHit(new boolean[3]);
		Arrays.fill(this.getHit(), false);
	}
	
	@Override
	public String getShipType() {
		return "submarine";
	}
}
