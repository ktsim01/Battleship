import java.util.LinkedList;
import java.util.Queue;

public class KyutaeSimStrategy extends ComputerBattleshipPlayer {
	private boolean up, right, left, down, sinking;
	private Position previous;
	private int counter;
	private Position start;
	private int length;
	private String orient;
	private Queue<Position> temp;
	private boolean duplicate;
	private Position inarow;
	private boolean four;
	private boolean wrong;
	private boolean hole;

	public void startGame() {
		super.startGame();
		up = false;
		right = false;
		left = false;
		down = false;
		sinking = false;
		counter = 0;
		length = 0;
		orient = "";
		previous = null;
		start = null;
		temp = new LinkedList<>();
		duplicate = false;
		inarow = null;
		four = false;
		wrong = false;
		hole = false;

	}

	public String playerName() {
		return "Kyutae Sim's Strategy";
	}

	public String author() {
		return "Kyutae Sim";
	}

	public Position shoot() {
		//checkhole();
		if (wrong || hole) {
			random();
		}

		//if(four) { print(); }

		if (previous != null && getGrid().hit(previous) && !sinking) {
			sinking = true;
			start = previous;
			checkLength(start);
		}
		/*
		 * if (!temp.isEmpty() && !sinking) { reset(); previous = temp.remove(); start =
		 * previous; sinking = true; checkLength(start); previous = target(start);
		 * return previous; }
		 */
		if (!sinking) {
			previous = hunt();
			return previous;
		} else {
			if (getGrid().hit(previous))
				counter++;
			if (getGrid().hit(previous) && getGrid().boatInitial(previous) != getGrid().boatInitial(start)) {
				temp.add(previous);
				counter--;
				if (temp.size() >= 2 && getGrid().boatInitial(previous) == getGrid().boatInitial(temp.peek())) {
					inarow = temp.remove();

				}

				if (temp.size() >= 2)
					duplicate = true;
				if (temp.size() == 3) {
					four = true;
				}
				if (counter >= 2) {
					previous = reverse(start);
					return previous;
				}
				if (counter == 1) {
					previous = target(start);
					return previous;
				}
			}
			if (counter == length) {
				reset();
				if (!temp.isEmpty() && !sinking) {
					reset();
					counter = 1;
					previous = temp.remove();
					start = previous;
					sinking = true;
					checkLength(start);

					previous = target(start);
					// System.out.println(temp.size());
					if (duplicate && temp.isEmpty() || four) {
						counter = Dcheck(start);
						if (counter == 2) {
							counter = 1;
						}
						if (counter == 3) {
							counter = 2;
							start = inarow;
							previous = afterFirstHit(new Position(start.rowIndex() - 1, start.columnIndex()));
						}
						duplicate = false;
						/*
						 * if(four) { four=false; }
						 */

					}

					return previous;
				}

				else {
					previous = hunt();
					return previous;
				}

			}

			else if (getGrid().hit(previous) && counter == 1) {
				previous = target(start);
				return previous;
			} else if (getGrid().hit(previous) && counter >= 2 & counter < length) {
				if (!getGrid().empty(afterFirstHit(previous))) {
					previous = reverse(start);
					return previous;

				} else if (getGrid().boatInitial(previous) == getGrid().boatInitial(start)) {
					previous = afterFirstHit(previous);
					return previous;
				}

			} else if (getGrid().miss(previous) && counter == 1) {
				previous = target(start);
				return previous;
			} else if (counter >= 2 && getGrid().miss(previous)) {
				previous = reverse(start);
				return previous;
			}

			if (previous.rowIndex() == start.rowIndex() + 1 && orient.equals("vertical")) {
				return previous;
			}
			if (previous.columnIndex() == start.columnIndex() - 1 && orient.equals("horizontal")) {
				return previous;
			}

			System.out.println(previous.rowIndex());
			System.out.println(previous.columnIndex());
			previous = target(start);
			return previous;
		}

	}

	public int Dcheck(Position p) {
		int hits = 0;
		for (int i = 0; i < 10; i++) {
			if (getGrid().boatInitial(new Position(i, p.columnIndex())) == getGrid().boatInitial(p)) {
				hits++;
			}
		}
		for (int i = 0; i < 10; i++) {
			if (getGrid().boatInitial(new Position(p.rowIndex(), i)) == getGrid().boatInitial(p)) {
				hits++;
			}
		}
		return hits;
	}

	public Position hunt() {
		int row, column;
		Position trying;
		row = (int) (Math.random() * 10);
		if (row % 2 == 0) {
			column = 2 * (int) (Math.random() * 5);
		} else {
			column = 2 * (int) (Math.random() * 5) + 1;
		}
		trying = new Position(row, column);
		while (!getGrid().empty(trying)) {
			row = (int) (Math.random() * 10);
			if (row % 2 == 0) {
				column = 2 * (int) (Math.random() * 5);
			} else {
				column = 2 * (int) (Math.random() * 5) + 1;
			}
			trying = new Position(row, column);

		}
		return trying;
	}

	public void checkLength(Position p) {
		char c = getGrid().boatInitial(p);

		if (c == 'A') {
			length = 5;
		}
		if (c == 'B') {
			length = 4;
		}
		if (c == 'C') {
			length = 3;
		}
		if (c == 'D') {
			length = 2;
		}
		if (c == 'S') {
			length = 3;
		}

	}

	public void updatePlayer(Position pos, boolean hit, char initial, String boatName, boolean sunk, boolean gameOver,
			boolean tooManyTurns, int turns) {
		super.updatePlayer(pos, hit, initial, boatName, sunk, gameOver, tooManyTurns, turns);
		super.updateGrid(pos, hit, initial);
	}

	public void print() {
		Position check;
		System.out.print(" ");
		for (int x = 1; x <= 10; x++) {
			System.out.print(" " + x);
		}
		System.out.println();
		for (int y = 0; y < 10; y++) {
			System.out.print((char) (y + 65) + " ");
			for (int x = 0; x < 10; x++) {
				check = new Position(y, x);
				if (getGrid().hit(check))
					System.out.print(getGrid().boatInitial(check));
				else if (getGrid().miss(check))
					System.out.print('*');
				else
					System.out.print('.');
				System.out.print(" ");

			}
			System.out.println();
		}
	}

	public void checkhole() {

		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (getGrid().empty(new Position(i, j))) {
					if (getGrid().boatInitial(new Position(i + 1, j)) == getGrid()
							.boatInitial(new Position(i - 1, j))) {
						hole = true;
						//System.out.println("HERE");
					}

				}
			}
		}
	}

	public void reset() {
		up = false;
		down = false;
		right = false;
		left = false;
		sinking = false;
		counter = 0;
		length = 0;
	}

	public Position afterFirstHit(Position p) {

		if (start.columnIndex() == p.columnIndex()) {
			orient = "vertical";

			if (p.rowIndex() > start.rowIndex()) {
				if (p.rowIndex() == 9) {
					previous = new Position(start.rowIndex() - 1, start.columnIndex());
					return previous;
				}
				return new Position(p.rowIndex() + 1, p.columnIndex());
			}
			if (p.rowIndex() < start.rowIndex()) {
				if (p.rowIndex() == 0) {
					previous = new Position(start.rowIndex() + 1, start.columnIndex());
					return previous;
				}
				return new Position(p.rowIndex() - 1, p.columnIndex());
			}
		}
		if (start.rowIndex() == p.rowIndex()) {
			orient = "horizontal";

			if (p.columnIndex() > start.columnIndex()) {
				if (p.columnIndex() == 9) {
					previous = new Position(start.rowIndex(), start.columnIndex() - 1);
					return previous;
				}
				return new Position(p.rowIndex(), p.columnIndex() + 1);
			}
			if (p.columnIndex() < start.columnIndex()) {
				if (p.columnIndex() == 0) {
					previous = new Position(start.rowIndex(), start.columnIndex() + 1);
					return start;
				}
				return new Position(p.rowIndex(), p.columnIndex() - 1);
			}

		}
		return null;

	}

	public Position reverse(Position p) {
		if (p.columnIndex() == previous.columnIndex()) {
			return new Position(p.rowIndex() + 1, p.columnIndex());
		}
		if (p.rowIndex() == previous.rowIndex()) {
			return new Position(p.rowIndex(), p.columnIndex() - 1);

		}
		System.out.println("reverse?");
		return null;
	}

	public Position target(Position p) {

		if (p.rowIndex() == 0)
			up = false;
		else {
			if (getGrid().empty(new Position(p.rowIndex() - 1, p.columnIndex())))
				up = true;
			else {
				up = false;
			}
		}
		if (p.columnIndex() == 9)
			right = false;
		else {
			if (getGrid().empty(new Position(p.rowIndex(), p.columnIndex() + 1)))
				right = true;
			else {
				right = false;
			}
		}
		if (p.columnIndex() == 0)
			left = false;
		else {
			if (getGrid().empty(new Position(p.rowIndex(), p.columnIndex() - 1)))
				left = true;
			else {
				left = false;
			}
		}
		if (p.rowIndex() == 9)
			down = false;
		else {
			if (getGrid().empty(new Position(p.rowIndex() + 1, p.columnIndex())))
				down = true;
			else {
				down = false;
			}
		}
		if (!up) {
			if (right)
				return new Position(p.rowIndex(), p.columnIndex() + 1);
			if (left)
				return new Position(p.rowIndex(), p.columnIndex() - 1);
			if (down)
				return new Position(p.rowIndex() + 1, p.columnIndex());
		} else {
			return new Position(p.rowIndex() - 1, p.columnIndex());
		}

		if (!right) {
			if (left)
				return new Position(p.rowIndex(), p.columnIndex() - 1);
			if (down)
				return new Position(p.rowIndex() + 1, p.columnIndex());
		} else {
			return new Position(p.rowIndex(), p.columnIndex() + 1);

		}
		if (!down) {
			if (left)
				return new Position(p.rowIndex(), p.columnIndex() - 1);
		} else {
			return new Position(p.rowIndex() + 1, p.columnIndex());

		}
		if (left)
			return new Position(p.rowIndex(), p.columnIndex() - 1);
		System.out.println("NO WAY in target");
		wrong = true;
		return random();
	}

	public Position random() {
		Position pos = new Position((int) (Math.random() * 10), (int) (Math.random() * 10));
		while (!getGrid().empty(pos)) {
			pos = new Position((int) (Math.random() * 10), (int) (Math.random() * 10));
		}
		
		return pos;

	}
}
