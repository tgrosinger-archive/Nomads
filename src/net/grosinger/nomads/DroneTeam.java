package net.grosinger.nomads;

/*
 * Contains a pointer to the first and the last DroneListItem in a particular team.
 * When adding drones to Team A they should be added to the end of the DroneTeam
 * So similar drones are grouped together
 */
public class DroneTeam {
	private DroneListItem first;
	private DroneListItem last;

	// Taken from the first drone added to the team
	private String teamName;

	public DroneTeam(DroneListItem firstDrone) {
		first = firstDrone;
		last = firstDrone;
	}

	/*
	 * Getters and Setters
	 */
	public DroneListItem getFirst() {
		return first;
	}

	public DroneListItem getLast() {
		return last;
	}

	public String getName() {
		return teamName;
	}

	public void setFirst(DroneListItem theFirst) {
		first = theFirst;
	}

	public void setLast(DroneListItem theLast) {
		last = theLast;
	}

	public void setName(String newName) {
		teamName = newName;
	}

	/*
	 * Adds a DroneListItem to the end of a DroneTeam
	 */
	public void addToEnd(DroneListItem newItem) {
		// TODO - Implement addToEnd
	}

	/*
	 * Always remove a DroneListItem through the DroneTeam so that it can tell
	 * if it needs to update the first or last pointers
	 */
	public void removeDrone(DroneListItem toRemove) {
		// TODO - Implement removeDrone
	}
}
