package net.grosinger.nomads;

/*
 * A class that allows a drone to be part of a linked list
 * Provides reference to the next drone, the previous drone, and the drone object
 * 
 * Previous --> Towards the first item
 * Next     --> Towards the last item
 */
public class DroneListItem {
	private DroneListItem next;
	private DroneListItem previous;
	private Drone current;

	/*
	 * Default constructor, includes all references
	 */
	public DroneListItem(DroneListItem theNext, DroneListItem thePrevious, Drone theCurrent) {
		next = theNext;
		previous = thePrevious;
		current = theCurrent;
	}

	/*
	 * Constructor for the first or last Drone
	 */
	public DroneListItem(DroneListItem otherDrone, Drone theCurrent, Boolean isFirst) {
		if (isFirst)
			new DroneListItem(otherDrone, null, theCurrent);
		else
			new DroneListItem(null, otherDrone, theCurrent);
	}

	/*
	 * Getters and Setters
	 */
	public DroneListItem getNext() {
		return next;
	}

	public DroneListItem getPrevious() {
		return previous;
	}

	public Drone getCurrent() {
		return current;
	}

	public void setNext(DroneListItem theNext) {
		next = theNext;
	}

	public void setPrevious(DroneListItem thePrevious) {
		previous = thePrevious;
	}

	// Should be no need to set the current
}
