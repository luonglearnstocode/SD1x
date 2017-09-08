package week4;

import java.util.Scanner;

/*
 * Battleship is usually a two-player game, where each player has a fleet and an ocean (hidden from the other player), 
 * and tries to be the first to sink the other player’s fleet. 
 * We will do just a solo version, where the computer places the ships, and the human attempts to sink them.
 * 
 * We’ll play this game on a 20x20 ocean. This is larger than the ocean in the traditional battleship game.  
 * In this game we will have one 8-square Battleship, one 7-square Battlecruiser, two 6-square Cruisers, two 5-square Light Cruisers, three 4-square Destroyers and four 3-square Submarines. 
 * Finally, unlike the traditional game, A player can shoot 5 times in each turn.
 * 
 * The computer places these 13 ships on the ocean in such a way that no ships are immediately adjacent to each other, 
 * either horizontally, vertically, or diagonally.
 * 
 * The human player does not know where the ships are. 
 * The initial display of the ocean shows a 20 by 20 array of locations, all the same.
 * 
 * The human player tries to hit the ships, by calling out a row and column number. 
 * The computer responds with one bit of information saying ”hit” or ”miss.” 
 * When a ship is hit but not sunk, the program does not provide any information about what kind of a ship was hit. 
 * However, when a ship is hit and sinks, the program prints out a message ”You just sank a ship-type.”
 * After each shot, the computer redisplays the ocean with the new information.
 *  
 * A ship is ”sunk” when every square of the ship has been hit. 
 * Thus, it takes 8 hits to sink a battleship but only 6 to sink a cruiser. 
 * The objective is to sink the fleet with as few shots as possible.
 * 
 * When all ships have been sunk, the program prints out a message that the game is over, and tells the user how many shots were required.
 */
public class BattleshipGame {
	
	/*
	 *  In this class you will set up the game; accept ”shots” from the user; display the results; 
	 *  print final scores; and ask the user if he/she wants to play again. 
	 *  All input/output is done here (although some of it is done by calling a print() method in the Ocean class.) 
	 *  All computation will be done in the Ocean class and the various Ship classes.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Ocean ocean = new Ocean();
		System.out.println("Welcome to BattleShip Game! \n");
		ocean.placeAllShipsRandomly();
		
		while (true) {
			System.out.println("Shot fired: " + ocean.getShotsFired());
			System.out.println("Hits: " + ocean.getHitCount());
			System.out.println("Ships sunk: " + ocean.getShipsSunk());
			System.out.println();
			ocean.print();
		
			/*
			 * autoplay the game
			 */
//			for (int i = 0; i < 10; i++) {
//				for (int j = 0; j < 10; j++) {
//					System.out.println(i + " " + j);
//					ocean.shootAt(i, j);
//					ocean.print();
//				}
//			}
//			ocean.print();
//			break;
			
			System.out.print("5 places to shoot at: ");
			String input = scanner.nextLine();
			if (input.equals("q")) {
				System.out.println("Game over!");
				break;
			}
			String[] pairs = input.split("; ");
			for (String pair : pairs) {
				String[] locations = pair.split(", ");
				if (ocean.shootAt(Integer.parseInt(locations[0]), Integer.parseInt(locations[1]))) {
					System.out.println("hit");
				} else {
					System.out.println("miss");
				}
			}
			
			if (ocean.isGameOver()) {
				System.out.print("Game over! Play again? y or n: ");
				input = scanner.nextLine();
				if (input.equals("y")) {
					ocean = new Ocean();
					ocean.placeAllShipsRandomly();
				} else {
					System.out.println("Total shot fired: " + ocean.getShotsFired());
					break;
				}
			}
			
			
		}
		
		
		
	}

}
