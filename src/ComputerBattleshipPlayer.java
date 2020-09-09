public class ComputerBattleshipPlayer extends BattleshipPlayer {

	public void updatePlayer(Position pos, boolean hit, char initial, String boatName, boolean sunk, boolean gameOver,
			boolean tooManyTurns, int turns) {
		updateGrid(pos, hit, initial);
	}

	public String playerName() {
		return "Computer Player";
	}

	public Position shoot() {
		Position pos = new Position((int) (Math.random() * 10), (int) (Math.random() * 10));
		while (!getGrid().empty(pos)) {
			pos = new Position((int) (Math.random() * 10), (int) (Math.random() * 10));
		}
		return pos;

	}
}
