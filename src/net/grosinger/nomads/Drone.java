package net.grosinger.nomads;

import net.grosinger.nomads.DroneListItem.EnumMove;

/**
 * All drones will extend this class. Gives them access to basic set of tools
 * they need such as movement, discovery, and world interaction.
 */
public class Drone extends GameObject {
	private int age;

	/**
	 * Class constructor All custom drones should call their super constructor
	 * at the end of their own.
	 */
	public Drone() {
		if (Nomads.DEBUGSTATUS)
			System.out.println("Drone initialized");
	}

	// Moving
	// When moving, North is positive Y axis and East is positive X axis

	/**
	 * Starts the move, can be used to perform maintenance operations before the actual move 
	 */
	public final EnumMove getMove() {
		if (Nomads.DEBUGMOVES)
			System.out.println("Drone " + name + " moving");
		return move();
	}

	/**
	 * The main move method for all Drones. Custom Drones should override this
	 * with their own.
	 */
	public EnumMove move() {
		return EnumMove.NoMove;
	}

	

	/**
	 * Checks to see if the drone can move North
	 * 
	 * @param amount
	 *            of Distance to travel
	 * @return True if the move is possible, false if is invalid
	 */
	public final boolean canMoveNorth() {
		return canMoveHelper(1, 0);
	}

	/**
	 * Checks to see if the drone can move South
	 * 
	 * @param amount
	 *            of Distance to travel
	 * @return True if the move is possible, false if is invalid
	 */
	public final boolean canMoveSouth() {
		return canMoveHelper(-1, 0);
	}

	/**
	 * Checks to see if the drone can move East
	 * 
	 * @param amount
	 *            of Distance to travel
	 * @return True if the move is possible, false if is invalid
	 */
	public final boolean canMoveEast() {
		return canMoveHelper(0, 1);
	}

	/**
	 * Checks to see if the drone can move West
	 * 
	 * @param amount
	 *            of Distance to travel
	 * @return True if the move is possible, false if is invalid
	 */
	public final boolean canMoveWest() {
		return canMoveHelper(0, -1);
	}

	/**
	 * @param amountN
	 *            The amount of desired travel North (negative will go South)
	 * @param amountE
	 *            The amount of desired travel East (negative will go West)
	 * @return True if the move is possible, false if is invalid
	 */
	private final boolean canMoveHelper(int amountN, int amountE) {
		int size = Nomads.awesomeWorld.getWorldSize();
		if (getY() + amountN >= size || getY() + amountN < 0 || getX() + amountE >= size || getX() + amountE < 0) {
			if (Nomads.DEBUGMOVES)
				System.out.println("Cannot move, out of range");
			return false;
		} else if (Nomads.awesomeWorld.getObjectAt(getX() + amountE, getY() + amountN) != null) {
			if (Nomads.DEBUGMOVES)
				System.out.println("Cannot move, space occupied");
			return false;
		} else {
			if (Nomads.DEBUGMOVES)
				System.out.println("Can move");
			return true;
		}
	}

	// Getters and Setters

	/**
	 * Returns how many turns this drone has been alive
	 * 
	 * @return How many turns this drone has been alive
	 */
	public final int getAge() {
		return age;
	}

	// TODO - find a way to prevent the drone from accessing this
	/**
	 * Do not use under normal circumstances
	 * 
	 * @param newAge
	 *            Age to set as the Drone's current age
	 */
	public final void setAge(int newAge) {
		age = newAge;
	}

	/**
	 * Increases the age of the drone by 1 turn
	 */
	public final void incrementAge() {
		age++;
	}
}
