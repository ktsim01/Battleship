import java.util.*;

public class BattleshipPlayer {
	private String name;
	private int shotcount;
	private BattleshipGrid grid;
	Scanner sc = new Scanner(System.in);

	BattleshipPlayer() {
		name = "";

	}

	public void startGame() {
		if (name.equals("")) {
			System.out.println("Welcome to Battleship!");
			System.out.println("Please enter your name.");

			name = sc.nextLine();

		}
		grid = new BattleshipGrid();
	}

	public String playerName() {
		return name;
	}

	public Position shoot() {
		int column = 101;
		sc = new Scanner(System.in);
		System.out.print("Please enter a row to shoot at: ");

		String row = "";
		row = sc.next();

		while (!(row.charAt(0) <= 'J' && 'A' <= row.charAt(0) && row.length() == 1
				&& Character.isAlphabetic(row.charAt(0)))) {
			System.out.println("Invalid row. Please enter an alphabet that is A through J: ");
			row = sc.next();
		}
		System.out.println("Please enter a column to shoot at: ");
		sc = new Scanner(System.in);
		String temp;
		temp = sc.next();
		if (temp.length() == 1 && temp.charAt(0) <= 57 && temp.charAt(0) >= 49 || temp.equals("10"))
			column = Integer.parseInt(temp);
		else
			while (!(temp.length() == 1 && temp.charAt(0) <= 57 && temp.charAt(0) >= 49 || temp.equals("10"))) {
				System.out.println("Invalid column. Please enter an integer between 1 and 10 inclusive:  ");
				temp = sc.next();
			}
		column = Integer.parseInt(temp);

		Position pos = new Position(row.charAt(0), column);
		System.out.println(pos);
		shotcount++;
		return pos;

	}

	public void updateGrid(Position pos, boolean hit, char initial) {
		this.grid.shotAt(pos, hit, initial);
	}

	public BattleshipGrid getGrid() {
		return grid;
	}

	public void initializeGrid() {
		grid = new BattleshipGrid();
	}

	public void updatePlayer(Position pos, boolean hit, char initial, String boatName, boolean sunk, boolean gameOver,
			boolean tooManyTurns, int turns) {
		Position check;
		updateGrid(pos, hit, initial);
		System.out.print(" ");
		for (int x = 1; x <= 10; x++) {
			System.out.print(" " + x);
		}
		System.out.println();
		for (int y = 0; y < 10; y++) {
			System.out.print((char) (y + 65) + " ");
			for (int x = 0; x < 10; x++) {
				check = new Position(y, x);
				if (grid.hit(check))
					System.out.print(grid.boatInitial(check));
				else if (grid.miss(check))
					System.out.print('*');
				else
					System.out.print('.');
				System.out.print(" ");

			}
			System.out.println();
		}

		if (hit) {
			System.out.println("Your shot on " + boatName + " at " + pos + " was a hit");
		} else {
			System.out.println("Your shot on " + boatName + " at " + pos + " was a miss");
		}
		if (hit && sunk) {
			System.out.println("This " + boatName + " was sunk");
		} else if (hit) {
			System.out.println("This " + boatName + " was not sunk");
		}
		if (gameOver) {
			System.out.println("Game over");

		} else {
			System.out.println("Game is not over yet");
		}
		if (tooManyTurns) {
			System.out.println("You took too many turns");
		}
		System.out.println("Number of turns: " + turns);

	}

}
