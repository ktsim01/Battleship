public class Tester{
	public static void main(String args[]) {


		
		ComputerBattleshipPlayer computer= new KyutaeSimStrategy();
		
		PlayerEvaluator eval=new PlayerEvaluator(computer,5);
		System.out.println(eval.maxTurns());
		System.out.println(eval.minTurns());
		System.out.println(eval.averageTurns());


	}
	
	
	
}
