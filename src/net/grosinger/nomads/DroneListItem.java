package net.grosinger.nomads;

/*
 * A class that allows a drone to be part of a linked list
 * Provides reference to the next drone, the previous drone, and the drone object
 * 
 * Previous --> Towards the first item
 * Next     --> Towards the last item
 */
public class DroneListItem {
	private Drone next;
	private Drone previous;
	private Drone current;

	/*
	 * Default constructor, includes all references
	 */
	public DroneListItem(Drone theNext, Drone thePrevious, Drone theCurrent) {
		next = theNext;
		previous = thePrevious;
		current = theCurrent;
	}

	/*
	 * Constructor for the first or last Drone
	 */
	public DroneListItem(Drone otherDrone, Drone theCurrent, Boolean isFirst) {
		// TODO - Did I do this right?
		if (isFirst)
			new DroneListItem(otherDrone, null, theCurrent);
		else
			new DroneListItem(null, otherDrone, theCurrent);
	}

	/*
	 * Getters and Setters
	 */
	public Drone getNext() {
		return next;
	}

	public Drone getPrevious() {
		return previous;
	}

	public Drone getCurrent() {
		return current;
	}

	public void setNext(Drone theNext) {
		next = theNext;
	}

	public void setPrevious(Drone thePrevious) {
		previous = thePrevious;
	}

	// Should be no need to set the current
}
