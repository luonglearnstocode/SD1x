package week4;

import static org.junit.Assert.*;

import org.junit.Test;

public class OceanTest {

	@Test
	public void testConstructor() {
		Ocean ocean = new Ocean();
		assertEquals(0, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertEquals(0, ocean.getShotsFired());
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				assertEquals("empty", ocean.getShipArray()[i][j].getShipType());
			}
		}
		
	}
	
	@Test
	public void testPlaceAllShipsRandomly() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		assertEquals(0, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertEquals(0, ocean.getShotsFired());
		int shipCount = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (!ocean.getShipArray()[i][j].getShipType().equals("empty")) {
					shipCount++;
				}
				
			}
		}
		// 8 + 7 + 2*6 + 2*5 + 3*4 + 4*3 = 61
		assertEquals(61, shipCount);
	}
	
	@Test
	public void testIsOccupied1() {
		Ocean ocean = new Ocean();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				assertFalse( ocean.isOccupied(i, j));
			}
		}
	}
	
	@Test
	public void testIsOccupied2() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 8; j++) {
			assertTrue( ocean.isOccupied(0, j));
		}
		assertFalse( ocean.isOccupied(0, 8));
	}
	
	@Test
	public void testShootAt1() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		ocean.shootAt(0, 0);
		ocean.shootAt(0, 0);
		ocean.shootAt(1, 0);
		assertEquals(2, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertEquals(3, ocean.getShotsFired());
	}
	
	@Test
	public void testShootAt2() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 8; j++) {
			ocean.shootAt(0, j);
		}
		ocean.shootAt(0, 1);
		ocean.shootAt(0, 2);
		
		assertEquals(8, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		assertEquals(10, ocean.getShotsFired());
		
	}
	
	@Test
	public void testToString() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 8; j++) {
			ocean.shootAt(0, j);
		}
		
		assertEquals(8, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		assertEquals(8, ocean.getShotsFired());
		
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int i = 0; i < 20; i++) {
			sb.append(String.format("%3d", i));
		}
		sb.append("\n");
		
		for (int i = 0; i < 20; i++) {
			sb.append(String.format("%2d ", i));
			for (int j = 0; j < 20; j++) {
				if (i == 0 && j < 8) {
					sb.append("x  ");
				} else {
					sb.append(".  ");
				}				
			}
			sb.append("\n");
		}
		
		assertEquals(sb.toString(), ocean.toString());
		
	}
	
	@Test
	public void testToString2() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 7; j++) {
			ocean.shootAt(0, j);
		}
		
		assertEquals(7, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertEquals(7, ocean.getShotsFired());
		
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int i = 0; i < 20; i++) {
			sb.append(String.format("%3d", i));
		}
		sb.append("\n");
		
		for (int i = 0; i < 20; i++) {
			sb.append(String.format("%2d ", i));
			for (int j = 0; j < 20; j++) {
				if (i == 0 && j < 7) {
					sb.append("S  ");
				} else {
					sb.append(".  ");
				}				
			}
			sb.append("\n");
		}
		
		assertEquals(sb.toString(), ocean.toString());
		
	}
	
	@Test
	public void testToString3() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 7; j++) {
			ocean.shootAt(1, j);
		}
		ocean.shootAt(1, 1);
		assertEquals(0, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertEquals(8, ocean.getShotsFired());
		
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int i = 0; i < 20; i++) {
			sb.append(String.format("%3d", i));
		}
		sb.append("\n");
		
		for (int i = 0; i < 20; i++) {
			sb.append(String.format("%2d ", i));
			for (int j = 0; j < 20; j++) {
				if (i == 1 && j < 7) {
					sb.append("-  ");
				} else {
					sb.append(".  ");
				}				
			}
			sb.append("\n");
		}
		
		assertEquals(sb.toString(), ocean.toString());
		
	}
}
