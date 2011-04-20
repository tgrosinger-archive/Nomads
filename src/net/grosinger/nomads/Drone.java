package net.grosinger.nomads;

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
		if (name == null) {
			System.out.println("Drone initialization failure, unnammed drone");
			// TODO - Implement method of ending the game prematurely
		} else if (Nomads.DEBUGSTATUS)
			System.out.println("Drone initialized");
	}

	// Moving
	// When moving, North is positive Y axis and East is positive X axis

	/**
	 * The main move method for all Drones. Custom Drones should override this
	 * with their own.
	 */
	public void move() {

	}

	/**
	 * Tests if a move North is possible. Will make the requested move if
	 * possible.
	 * 
	 * @param Amount
	 *            of distance to move North
	 * @return True if the move was made, false if is invalid
	 */
	public final boolean moveNorth(int amount) {
		if (canMoveNorth(amount)) {
			moveHelper(amount, 0);
			return true;
		} else
			return false;
	}

	/**
	 * Tests if a move South is possible. Will make the requested move if
	 * possible.
	 * 
	 * @param Amount
	 *            of distance to move South
	 * @return True if the move was made, false if is invalid
	 */
	public final boolean moveSouth(int amount) {
		if (canMoveSouth(amount)) {
			moveHelper(amount * -1, 0);
			return true;
		} else
			return false;
	}

	/**
	 * Tests if a move East is possible. Will make the requested move if
	 * possible.
	 * 
	 * @param Amount
	 *            of distance to move East
	 * @return True if the move was made, false if is invalid
	 */
	public final boolean moveEast(int amount) {
		if (canMoveEast(amount)) {
			moveHelper(0, amount);
			return true;
		} else
			return false;
	}

	/**
	 * Tests if a move West is possible. Will make the requested move if
	 * possible.
	 * 
	 * @param Amount
	 *            of distance to move West
	 * @return True if the move was made, false if is invalid
	 */
	public final boolean moveWest(int amount) {
		if (canMoveWest(amount)) {
			moveHelper(0, amount * -1);
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
		Nomads.awesomeWorld.moveObjectAt(getX(), getY(), amountN, amountE);
	}

	/**
	 * Checks to see if the drone can move North
	 * 
	 * @param Amount
	 *            of Distance to travel
	 * @return True if the move is possible, false if is invalid
	 */
	public final boolean canMoveNorth(int amount) {
		return canMoveHelper(amount, 0);
	}

	/**
	 * Checks to see if the drone can move South
	 * 
	 * @param Amount
	 *            of Distance to travel
	 * @return True if the move is possible, false if is invalid
	 */
	public final boolean canMoveSouth(int amount) {
		return canMoveHelper(amount * -1, 0);
	}

	/**
	 * Checks to see if the drone can move East
	 * 
	 * @param Amount
	 *            of Distance to travel
	 * @return True if the move is possible, false if is invalid
	 */
	public final boolean canMoveEast(int amount) {
		return canMoveHelper(0, amount);
	}

	/**
	 * Checks to see if the drone can move West
	 * 
	 * @param Amount
	 *            of Distance to travel
	 * @return True if the move is possible, false if is invalid
	 */
	public final boolean canMoveWest(int amount) {
		return canMoveHelper(0, amount * -1);
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
