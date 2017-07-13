package week4;

import java.util.Arrays;

public class LightCruiser extends Ship {
	public LightCruiser() {
		this.setLength(5);
		this.setHit(new boolean[5]);
		Arrays.fill(this.getHit(), false);
	}
	
	@Override
	public String getShipType() {
		return "light cruiser";
	}
}
