package net.grosinger.nomads;

/**
 * A goal for a drone to collect. Holds a monetary reward if returned to a
 * TownCenter by the Drone that issued it.
 */
public class Objective implements GameObject {
	private int bounty;
	private String UID;
	private String name;

	public Objective(int newBounty, String newUID) {
		bounty = newBounty;
		UID = newUID;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Return the bounty associated with this objective
	 * 
	 * @return <code>int</code>
	 */
	public int getBounty() {
		return bounty;
	}

	/**
	 * Return the UID of the Drone that can pick this Objective up
	 * 
	 * @return <code>String</code>
	 */
	public String getUID() {
		return UID;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

}
