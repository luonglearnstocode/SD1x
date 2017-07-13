package week4;

import java.util.Scanner;

public class BattleshipGame {

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
			
//			for (int i = 0; i < 20; i++) {
//				for (int j = 0; j < 20; j++) {
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
