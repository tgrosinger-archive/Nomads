package net.grosinger.nomads;

/*
 * A class that allows a drone to be part of a linked list
 * Provides reference to the next drone, the previous drone, and the drone object
 * 
 * +----------------------------------------------------------+
 * | Most of the inner workings of the program that we do not |
 * | want a drone to have access to will take place here      |
 * +----------------------------------------------------------+
 * 
 * Previous --> Towards the first item
 * Next     --> Towards the last item
 */
public class DroneListItem {
	private DroneListItem next;
	private DroneListItem previous;
	private Drone current;

	public enum EnumMove {
		NoMove, North, South, East, West, Upgrade, Attack
	}

	// Stats about this robot

	private int visibleDistance;
	private int lumaLocatorDistance;
	private int objectLocatorDistance;
	private int reliability;
	private int defenses;
	private int speed; // Reflected in movements per turn
	private int turning;
	private int cargoSpace;

	/*
	 * Default constructor, includes all references
	 */
	public DroneListItem(DroneListItem theNext, DroneListItem thePrevious, Drone theCurrent) {
		next = theNext;
		previous = thePrevious;
		current = theCurrent;
		visibleDistance = 15;
	}

	// Getters and Setters

	/**
	 * Retrieve the next DroneListItem in the Linked List
	 * 
	 * @return <code>DroneListItem</code>
	 */
	public DroneListItem getNext() {
		return next;
	}

	/**
	 * Retrieve the previous DroneListItem in the Linked List
	 * 
	 * @return <code>DroneListItem</code>
	 */
	public DroneListItem getPrevious() {
		return previous;
	}

	/**
	 * Retrieve the Drone associated with the current DroneListItem
	 * 
	 * @return <code>Drone</code>
	 */
	public Drone getCurrent() {
		return current;
	}

	/**
	 * Retrieve the distance this drone can see other drones
	 * 
	 * @return <code>int</code> Visible Distance
	 */
	public int getVisibleDistance() {
		return visibleDistance;
	}

	/**
	 * Retrieve the distance from which this drone can spot a LumaPile
	 * 
	 * @return <code>int</code> Visible Distance
	 */
	public int getLumaLocatorDistance() {
		return lumaLocatorDistance;
	}

	/**
	 * Retrieve the distance from which this drone can spot an object
	 * 
	 * @return <code>int</code> Visible Distance
	 */
	public int getObjectLocatorDistance() {
		return objectLocatorDistance;
	}

	/**
	 * Retrieve the reliability factor of this drone
	 * 
	 * @return <code>int</code>
	 */
	public int getReliability() {
		return reliability;
	}

	/**
	 * Retrieve the defenses factor of this drone
	 * 
	 * @return <code>int</code>
	 */
	public int getDefenses() {
		return defenses;
	}

	/**
	 * Retrieve the speed factor of this drone
	 * 
	 * @return <code>int</code>
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Retrieve the turning factor of this drone
	 * 
	 * @return <code>int</code>
	 */
	public int getTurning() {
		return turning;
	}

	/**
	 * Retrieve the total space in the cargo hold of this drone. Does include
	 * space that is currently occupied.
	 * 
	 * @return <code>int</code>
	 */
	public int getCargoSpace() {
		return cargoSpace;
	}

	/**
	 * Sets the next DroneListItem in the Linked List
	 * 
	 * @param theNext
	 *            <code>DroneListItem</code>
	 */
	public void setNext(DroneListItem theNext) {
		next = theNext;
	}

	/**
	 * Sets the previous DroneListItem in the Linked List
	 * 
	 * @param thePrevious
	 *            <code>DroneListItem</code>
	 */
	public void setPrevious(DroneListItem thePrevious) {
		previous = thePrevious;
	}

	/**
	 * Sets the visible distance for this drone
	 * 
	 * @param newDistance
	 *            <code>int</code> New Distance
	 */
	private void setVisibleDistance(int newDistance) {
		visibleDistance = newDistance;
	}

	/**
	 * Increases the Visible Distance by specified amount
	 * 
	 * @param amount
	 *            <code>int</code> How much to increase the distance
	 */
	private void increaseVisibleDistance(int amount) {
		visibleDistance += amount;
	}

	// Actions

	/**
	 * Will ask the Drone what direction it would like to move
	 * 
	 * @return
	 */
	public boolean makeMove() {
		// Call the Drone's Move method
		EnumMove move = current.getMove();

		switch (move) {
		case NoMove: {
			// Default move has not been overridden
			return true;
		}
		case North: {
			moveNorth();
			return true;
		}
		case South: {
			moveSouth();
			return true;
		}
		case East: {
			moveEast();
			return true;
		}
		case West: {
			moveWest();
			return true;
		}
		case Upgrade: {
			//TODO - Implement upgrade
			return true;
		}
		case Attack: {
			//TODO - Implement attack
			return true;
		}
		default: {
			// No move was made
			return false;
		}
		}
	}

	// Movement

	/**
	 * Tests if a move North is possible. Will make the requested move if
	 * possible.
	 * 
	 * @param amount
	 *            of distance to move North
	 * @return True if the move was made, false if is invalid
	 */
	public final boolean moveNorth() {
		if (current.canMoveNorth()) {
			moveHelper(1, 0);
			return true;
		} else
			return false;
	}

	/**
	 * Tests if a move South is possible. Will make the requested move if
	 * possible.
	 * 
	 * @param amount
	 *            of distance to move South
	 * @return True if the move was made, false if is invalid
	 */
	public final boolean moveSouth() {
		if (current.canMoveSouth()) {
			moveHelper(-1, 0);
			return true;
		} else
			return false;
	}

	/**
	 * Tests if a move East is possible. Will make the requested move if
	 * possible.
	 * 
	 * @param amount
	 *            of distance to move East
	 * @return True if the move was made, false if is invalid
	 */
	public final boolean moveEast() {
		if (current.canMoveEast()) {
			moveHelper(0, 1);
			return true;
		} else
			return false;
	}

	/**
	 * Tests if a move West is possible. Will make the requested move if
	 * possible.
	 * 
	 * @param amount
	 *            of distance to move West
	 * @return True if the move was made, false if is invalid
	 */
	public final boolean moveWest() {
		if (current.canMoveWest()) {
			moveHelper(0,-1);
			return true;
		} else
			return false;
	}

	/**
	 * Performs a move in the specified amounts
	 * 
	 * @param amountN
	 *            Amount of distance to move North (negative will go South)
	 * @param amountE
	 *            Amount of distance to move East (negative will go West)
	 */
	public final void moveHelper(int amountN, int amountE) {
		Nomads.awesomeWorld.moveObjectAt(current.getX(), current.getY(), amountN, amountE);
	}
}
