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
	private DroneTeam team;

	/**
	 * The DroneTools for this Drone
	 */
	private DroneTools yourTools;

	public enum EnumMove {
		NoMove, North, South, East, West, Upgrade, Attack, Steal
	}

	public enum Direction {
		N, S, E, W
	}

	// Stats about this robot

	private int visibleDistance;
	private int lumaLocatorDistance;
	private int objectLocatorDistance;
	private int reliability;
	private int defenses;
	private int speed; // Reflected in movements per turn
	private int cargoSpace;
	private int theft;

	// Info about this robot

	private int age;
	private int x;
	private int y;

	/*
	 * Default constructor, includes all references
	 */
	public DroneListItem(DroneListItem theNext, DroneListItem thePrevious, Drone theCurrent, DroneTeam theTeam) {
		next = theNext;
		previous = thePrevious;
		current = theCurrent;
		visibleDistance = 15;
		speed = 1;
		team = theTeam;

		// Place itself in the world
		Nomads.awesomeWorld.placeNewDrone(this);

		// Give the Drone it's tools
		yourTools = new DroneTools(current, this, Nomads.awesomeWorld);
		current.setDroneTools(yourTools);
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
	 * Retrieve the total space in the cargo hold of this drone. Does include
	 * 
	 * space that is currently occupied.
	 * 
	 * @return <code>int</code>
	 */
	public int getCargoSpace() {
		return cargoSpace;
	}

	/**
	 * Retrieve the level of this drone in theiving
	 * 
	 * @return <code>int</code>
	 */
	public int getTheft() {
		return theft;
	}

	/**
	 * Returns how many turns this drone has been alive
	 * 
	 * @return How many turns this drone has been alive
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Returns the x index of this drone
	 * 
	 * @return <code>int</code>
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y index of this drone
	 * 
	 * @return <code>int</code>
	 */
	public int getY() {
		return y;
	}

	/**
	 * Retrieve reference to the team this drone belongs to
	 * 
	 * @return <code>DroneTeam</code>
	 */
	public DroneTeam getTeam() {
		return team;
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
	public void setVisibleDistance(int newDistance) {
		visibleDistance = newDistance;
	}

	/**
	 * Used when adding the drone to the map
	 * 
	 * @param newX
	 *            <code>int</code> new X location
	 */
	public void setX(int newX) {
		x = newX;
	}

	/**
	 * Used when adding the drone to the map
	 * 
	 * @param newY
	 *            <code>int</code> new Y location
	 */
	public void setY(int newY) {
		y = newY;
	}

	/**
	 * Increases the Visible Distance by specified amount
	 * 
	 * @param amount
	 *            <code>int</code> How much to increase the distance
	 */
	public void increaseVisibleDistance(int amount) {
		visibleDistance += amount;
	}

	/**
	 * Increases the age of the drone by 1 turn
	 */
	public final void incrementAge() {
		age++;
	}

	// Actions

	/**
	 * Will ask the Drone what direction it would like to move
	 * 
	 * @return <code>boolean</code> if a move was made
	 */
	public boolean makeMove() {
		// Call the Drone's Move method
		EnumMove move = current.move();

		switch (move) {
		case NoMove: {
			// Default move has not been overridden
			return true;
		}
		case North: {
			if (yourTools.canMoveNorth()) {
				moveDrone(Direction.N);
				return true;
			} else
				return false;
		}
		case South: {
			if (yourTools.canMoveSouth()) {
				moveDrone(Direction.S);
				return true;
			} else
				return false;
		}
		case East: {
			if (yourTools.canMoveEast()) {
				moveDrone(Direction.E);
				return true;
			} else
				return false;
		}
		case West: {
			if (yourTools.canMoveWest()) {
				moveDrone(Direction.W);
				return true;
			} else
				return false;
		}
		case Upgrade: {
			// TODO - Implement upgrade
			return true;
		}
		case Attack: {
			// TODO - Implement attack
			return true;
		}
		case Steal: {
			// TODO - Implement steal
			return true;
		}
		default: {
			// No move was made
			return false;
		}
		}
	}

	// Movement

	public void moveDrone(Direction direction) {
		int amountN = 0;
		int amountE = 0;
		switch (direction) {
		case N: {
			amountN = 1;
			break;
		}
		case S: {
			amountN = -1;
			break;
		}
		case E: {
			amountE = 1;
			break;
		}
		case W: {
			amountE = -1;
			break;
		}
		}

		// Make the move
		Nomads.awesomeWorld.moveObjectAt(getX(), getY(), amountN, amountE);

		// Update the saved coordinates
		if (amountN != 0)
			setY(getY() + amountN);
		if (amountE != 0)
			setX(getX() + amountE);
	}
}
