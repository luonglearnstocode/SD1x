package week4;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {

	@Test
	public void testGetShipType() {
		Ship ship = new BattleShip();
		assertEquals("battleship", ship.getShipType());
		assertEquals(8, ship.getLength());
	}
	
	@Test
	public void testGetShipType2() {
		Ship ship = new BattleCruiser();
		assertEquals("battlecruiser", ship.getShipType());
		assertEquals(7, ship.getLength());
	}
	
	@Test
	public void testOkToPlaceShipAt() {
		Ship ship = new BattleShip();
		Ocean ocean = new Ocean();
		assertFalse(ship.okToPlaceShipAt(0, 17, true, ocean));
		assertTrue(ship.okToPlaceShipAt(0, 10, true, ocean));
		assertFalse(ship.okToPlaceShipAt(15, 17, false, ocean));
		assertTrue(ship.okToPlaceShipAt(12, 10, false, ocean));
	}
	
	@Test
	public void testOkToPlaceShipAt2() {
		Ship ship1 = new BattleShip();
		Ship ship2 = new BattleShip();
		Ocean ocean = new Ocean();
		assertTrue(ship1.okToPlaceShipAt(0, 10, true, ocean));
		ship1.placeShipAt(0, 10, true, ocean);
		assertTrue(ship2.okToPlaceShipAt(1, 0, false, ocean));
		assertTrue(ship2.okToPlaceShipAt(1, 0, true, ocean));
		assertFalse(ship2.okToPlaceShipAt(1, 4, true, ocean));
		assertFalse(ship2.okToPlaceShipAt(1, 13, false, ocean));
	}
	
	@Test
	public void testIsSunk() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 8; j++) {
			ocean.shootAt(0, j);
		}
		
		assertTrue(ship.isSunk());
		
	}
	
	@Test
	public void testIsSunk2() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 7; j++) {
			ocean.shootAt(0, j);
		}
		
		assertFalse(ship.isSunk());
		
	}
	
	@Test
	public void testToString() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 8; j++) {
			ocean.shootAt(0, j);
		}
		
		assertEquals("x", ship.toString());
		
	}
	
	@Test
	public void testToString2() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 7; j++) {
			ocean.shootAt(0, j);
		}
		
		assertEquals("S", ship.toString());
		
	}
	
	@Test
	public void testShootAt() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);

		assertTrue(ship.shootAt(0, 3));
		assertTrue(ship.shootAt(0, 4));
		assertFalse(ship.shootAt(1, 4));
		
	}
	
	@Test
	public void testShootAt2() {
		Ocean ocean = new Ocean();
		BattleShip ship = new BattleShip();
		ship.placeShipAt(0, 0, true, ocean);
		for (int j = 0; j < 8; j++) {
			ocean.shootAt(0, j);
		}
		
		assertFalse(ship.shootAt(0, 3));
		assertFalse(ship.shootAt(0, 4));
		assertFalse(ship.shootAt(1, 4));
		
	}

}
