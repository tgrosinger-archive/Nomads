package net.grosinger.nomads;

/**
 * Contains a pointer to the first and the last DroneListItem in a particular
 * team. When adding drones to Team A they should be added to the end of the
 * DroneTeam So similar drones are grouped together
 */
public class DroneTeam {

	/**
	 * The first DroneListItem in the team
	 */
	private DroneListItem first;

	/**
	 * The last DroneListItem in the team
	 */
	private DroneListItem last;

	/**
	 * Taken from the first drone added to the team
	 */
	private String teamName;

	/**
	 * Class Constructor
	 * 
	 * @param firstDrone
	 *            <code>DroneListItem</code> that will be the first Drone
	 */
	public DroneTeam(DroneListItem firstDrone) {
		first = firstDrone;
		last = firstDrone;
	}

	// Getters and Setters

	/**
	 * Retrieves the first DroneListItem
	 * 
	 * @return First <code>DroneListItem</code>
	 */
	public DroneListItem getFirst() {
		return first;
	}

	/**
	 * Retrieves the last DroneListItem
	 * 
	 * @return Last <code>DroneListItem</code>
	 */
	public DroneListItem getLast() {
		return last;
	}

	/**
	 * Retrieves the Team Name
	 * 
	 * @return Team Name in a <code>String</code>
	 */
	public String getName() {
		return teamName;
	}

	/**
	 * Sets the first DroneListItem to that provided
	 * 
	 * @param theFirst
	 *            <code>DroneListItem</code> to be made first
	 */
	public void setFirst(DroneListItem theFirst) {
		first = theFirst;
	}

	/**
	 * Sets the last DroneListItem to that provided
	 * 
	 * @param theFirst
	 *            <code>DroneListItem</code> to be made last
	 */
	public void setLast(DroneListItem theLast) {
		last = theLast;
	}

	/**
	 * Sets the Team Name
	 * 
	 * @param newName
	 *            <String> to set the name to
	 */
	public void setName(String newName) {
		teamName = newName;
	}

	/**
	 * Adds a DroneListItem to the end of a DroneTeam
	 * 
	 * @param newItem
	 *            <code>DroneListItem</code> to add to end
	 */
	public void addToEnd(DroneListItem newItem) {
		// TODO - Implement addToEnd
	}

	/**
	 * Always remove a DroneListItem through the DroneTeam so that it can tell
	 * if it needs to update the first or last pointers
	 * 
	 * @param toRemove
	 *            <code>DroneListItem</code> that needs to be removed
	 */
	public void removeDrone(DroneListItem toRemove) {
		// TODO - Implement removeDrone
	}
}
