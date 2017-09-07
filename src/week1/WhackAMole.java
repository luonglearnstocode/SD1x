package week1;
import java.util.*;

public class WhackAMole {
	int score;
	int molesLeft;
	int attemptsLeft;
	char[][] moleGrid; 
	
	/**
	 * Specifies total number of whacks allowed, and the grid dimension.  
	 * Initializes the moleGrid with the * character.
	 * 
	 * @param numAttempts the total number of whacks allowed 
	 * @param gridDimension of the moleGrid
	 */
	public WhackAMole(int numAttempts, int gridDimension) {
		this.score = 0;
		this.molesLeft = 0;
		this.attemptsLeft = numAttempts;
		this.moleGrid = new char[gridDimension][gridDimension];
		for (char[] r : moleGrid) {
			Arrays.fill(r, '*');
		}
		
	}
	
	/**
	 * Given a location, place a mole at that location. 
	 * Update number of moles left.
	 * Return whether it is possible to place. 
	 */
	public boolean place(int x, int y) {
		if (moleGrid[x][y] == 'M') return false;
		moleGrid[x][y] = 'M';
		molesLeft += 1;
		return true;
	}
	
	/**
	 * Given a location, take a whack at that location. 
	 * If that location contains a mole, the score, number of attemptsLeft, and molesLeft is updated. 
	 * If that location does not contain a mole, only attemptsLeft is updated.
	 */
	public void whack(int x, int y) {
		if (moleGrid[x][y] == 'M') {
			score += 10;
			molesLeft--;
			moleGrid[x][y] = 'W';
		}
		attemptsLeft--;
	}
	
	/**
	 * Print the grid without showing where the moles are. 
	 * For every spot that has recorded a “whacked mole” print out a W, 
	 * or * otherwise.
	 */
	public void printGridToUser() {
		for (char[] r : moleGrid) {
			for (char e : r) {
				if (e != 'W') {
					System.out.print("*");
				} else {
					System.out.print("W");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Print the grid completely. 
	 * This is effectively dumping the 2d array on the screen. 
	 * The places where the moles are should be printed as an ‘M’. 
	 * The places where the moles have been whacked should be printed as a ‘W’. 
	 * The places that don’t have a mole should be printed as *.
	 */
	public void printGrid() {
		for (char[] r : moleGrid) {
			for (char e : r) {
				if (e == 'M') {
					System.out.print("M");
				} else if (e == 'W') {
					System.out.print("W");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Begin by creating a 10 by 10 grid where you randomly place the moles. 
	 * Place a total of 10 moles.
	 * Now allow the user  to enter the x and y coordinates of where they would like to take a whack. 
	 * Tell them they have a maximum of 50 attempts to get all the moles. 
	 * At any point in the game, they can input coordinates of -1, -1 in order to indicate that they are giving up. 
	 * If the user gives up they get to see the entire grid.  
	 * The game ends if the user is able to uncover all the moles or if the user runs out of attempts. 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WhackAMole game = new WhackAMole(50, 10);
		Random random = new Random();
		for (int i = 0; i < 10; i++) { // randomly place 10 moles
			game.place(random.nextInt(10), random.nextInt(10));
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the game!");
		
		while (game.attemptsLeft > 0 && game.molesLeft > 0) {
			System.out.println("Attempts left: " + game.attemptsLeft);
			System.out.println("Score: " + game.score);
			game.printGridToUser();
			System.out.print("Where do you like to whack? ");
			
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			
			if (x == -1 && y == -1) {
				game.printGrid();
				break;
			}
			
			game.whack(x, y);
			
		
		}
		System.out.println("Game over!");
		
	}

}
