package week4;

import java.util.Arrays;

public class BattleCruiser extends Ship {
	public BattleCruiser() {
		this.setLength(7);
		this.setHit(new boolean[7]);
		Arrays.fill(this.getHit(), false);
	}
	
	@Override
	public String getShipType() {
		return "battlecruiser";
	}
}
