package week1;
import java.util.*;

public class WhackAMole {
	int score;
	int molesLeft;
	int attemptsLeft;
	char[][] moleGrid; 
	
	public WhackAMole(int numAttempts, int gridDimension) {
		this.score = 0;
		this.molesLeft = 0;
		this.attemptsLeft = numAttempts;
		this.moleGrid = new char[gridDimension][gridDimension];
		for (char[] r : moleGrid) {
			Arrays.fill(r, '*');
		}
		
	}
	
	public boolean place(int x, int y) {
		if (moleGrid[x][y] == 'M') return false;
		moleGrid[x][y] = 'M';
		molesLeft += 1;
		return true;
	}
	
	public void whack(int x, int y) {
		if (moleGrid[x][y] == 'M') {
			score += 10;
			molesLeft--;
			moleGrid[x][y] = 'W';
		}
		attemptsLeft--;
	}
	
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
