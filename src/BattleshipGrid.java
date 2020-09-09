public class BattleshipGrid {
	private char grid[][] = new char[10][10];

	public void shotAt(Position pos, boolean hit, char initial) {

		if (hit)
			grid[pos.rowIndex()][pos.columnIndex()] = initial;
		else
			grid[pos.rowIndex()][pos.columnIndex()] = 'X';
	}

	public boolean hit(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] != '\u0000' && 
grid[pos.rowIndex()][pos.columnIndex()] != 'X')
			return true;
		else
			return false;
	}

	public boolean miss(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] == 'X')
			return true;
		else
			return false;

	}

	public boolean empty(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] == '\u0000')
			return true;
		else
			return false;
	}

	public char boatInitial(Position pos) {
		return grid[pos.rowIndex()][pos.columnIndex()];
	}
}
