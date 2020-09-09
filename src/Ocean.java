public class Ocean extends GoodOcean {
	public void PlaceAllBoats() {
		int numboat = 0;
		double direction;
		String dir;
		String[] boatTypes = { "Aircraft Carrier", "Battleship", "Cruiser", "Destroyer", "Submarine" };
		while (numboat < 5) {
			direction = Math.random();
			Position pos = new Position((int) (Math.random() * 10), (int) (Math.random() * 10));
			if (direction > 0.5)
				dir = "horizontal";
			else dir = "vertical";
			try {
				placeBoat(boatTypes[numboat], dir, pos);
				numboat++;
			} catch (Exception e) {
			}
		}
		//printBoatLocations();
	}
}