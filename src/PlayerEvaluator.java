public class PlayerEvaluator {
	private int max = 0;
	private int min = 100;
	private float total = 0;

	PlayerEvaluator(ComputerBattleshipPlayer player, int runs) {
		int current = 0;
		for (int i = 0; i < runs; i++) {
			System.out.println(i);
			BattleshipGame game = new BattleshipGame(player);
			current = game.play();
			try{
				
				current = game.play();
			
			}
			catch(Exception ex) {
				for(int j=0;j<50;j++) {
					System.out.println("ERROR");
				}
				
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
						if (player.getGrid().hit(check))
							System.out.print(player.getGrid().boatInitial(check));
						else if (player.getGrid().miss(check))
							System.out.print('*');
						else
							System.out.print('.');
						System.out.print(" ");

					}
					System.out.println();
				}
			}
			total += current;
			if (current > max)
				max = current;
			if (current < min)
				min = current;
		}
		total = total / runs;

	}

	public int maxTurns() {
		return max;
	}

	public int minTurns() {
		return min;
	}

	public float averageTurns() {
		return total;
	}
}
