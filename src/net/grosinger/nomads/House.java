package net.grosinger.nomads;

/**
 * It is a house, just like it says. Basically a building that can be owned by a
 * team
 */
public class House extends Building {

	private String team;
	private int turnCreated;

	public House(Structure thisBuilding, int newX, int newY) {
		super(thisBuilding, newX, newY);
		turnCreated = Nomads.turn;
	}

	public House(Structure thisBuilding, int newX, int newY, String teamName) {
		super(thisBuilding, newX, newY);
		team = teamName;
		turnCreated = Nomads.turn;
	}

	/**
	 * Retrieve the name of team that owns this house
	 * 
	 * @return <code>String</code>
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * Calculates the age of the house
	 * 
	 * @return currentTurn - turnCreated
	 */
	public int getAge() {
		return Nomads.turn - turnCreated;
	}

	/**
	 * Sets the name of the team that owns this house
	 * 
	 * @param newTeam
	 *            <code>String</code> House owner
	 */
	public void setTeam(String newTeam) {
		team = newTeam;
	}
}
