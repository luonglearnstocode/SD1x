package week4;

import java.util.Arrays;

public class BattleShip extends Ship{
	public BattleShip() {
		this.setLength(8);
		this.setHit(new boolean[8]);
		Arrays.fill(this.getHit(), false);
	}
	
	@Override
	public String getShipType() {
		return "battleship";
	}
	
}
