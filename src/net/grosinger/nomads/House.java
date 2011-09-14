package net.grosinger.nomads;


/**
 * It is a house, just like it says. Basically a building that can be owned by a
 * team
 */
public class House extends Building {
	private int turnCreated;

	public House(Structure thisBuilding, int newX, int newY, DroneTeam team) {
		super(thisBuilding, newX, newY, team);
		turnCreated = Nomads.turn;
	}

	/**
	 * Calculates the age of the house
	 * 
	 * @return currentTurn - turnCreated
	 */
	public int getAge() {
		return Nomads.turn - turnCreated;
	}
}
