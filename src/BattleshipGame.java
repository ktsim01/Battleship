
public class BattleshipGame {
	private Ocean gameocean;
	private BattleshipPlayer p;
	private int turns;
	private Position shooting;
	

	BattleshipGame(BattleshipPlayer player) {
		gameocean = new Ocean();
		gameocean.PlaceAllBoats();
		p = player;
		turns = 0;
		p.startGame();
	}

	public int play() {
		while (!gameocean.allSunk() && turns < 100) {
			shooting = p.shoot();
			gameocean.shootAt(shooting);
			turns += 1;
			p.updatePlayer(shooting, gameocean.hit(shooting), gameocean.boatInitial(shooting), gameocean.boatName(shooting), gameocean.sunk(shooting), gameocean.allSunk(), turns >= 100, turns);

		}
		return turns;
	}
}
