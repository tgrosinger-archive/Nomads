package net.grosinger.nomads;

/*
 * All drones will extend this class to give them the basic set of tools that they need.
 * Don't forget to set a name
 */
public class Drone extends GameObject {
	private int age;

	/*
	 * All custom drones should call the super constructor at the end of their
	 * constructor
	 */
	public Drone() {
		if (name == null) {
			System.out.println("Drone initialization failure, unnammed drone");
			// TODO - implement method of ending the game prematurely
		} else if (Nomads.DEBUGSTATUS)
			System.out.println("Drone initialized");
	}

	// Moving
	// When moving, North is positive Y axis and East is positive X axis

	// To be overridden by the drones
	// This will be their turn
	public void move() {

	}

	// Checks to see if it can move north
	// Moves and returns true if it can, returns false if it can not
	public final boolean moveNorth(int amount) {
		if (canMoveNorth(amount)) {
			moveHelper(amount, 0);
			return true;
		} else
			return false;
	}

	// Checks to see if it can move south
	// Moves and returns true if it can, returns false if it can not
	public final boolean moveSouth(int amount) {
		if (canMoveSouth(amount)) {
			moveHelper(amount * -1, 0);
			return true;
		} else
			return false;
	}

	// Checks to see if it can move east
	// Moves and returns true if it can, returns false if it can not
	public final boolean moveEast(int amount) {
		if (canMoveEast(amount)) {
			moveHelper(0, amount);
			return true;
		} else
			return false;
	}

	// Checks to see if it can move west
	// Moves and returns true if it can, returns false if it can not
	public final boolean moveWest(int amount) {
		if (canMoveWest(amount)) {
			moveHelper(0, amount * -1);
			return true;
		} else
			return false;
	}

	public final void moveHelper(int amountN, int amountS) {
		// TODO - implement moveHelper method
	}

	// Checks to see if it can move north
	// Returns true if it can, false if it can not
	public final boolean canMoveNorth(int amount) {
		return canMoveHelper(amount, 0);
	}

	// Checks to see if it can move south
	// Returns true if it can, false if it can not
	public final boolean canMoveSouth(int amount) {
		return canMoveHelper(amount * -1, 0);
	}

	// Checks to see if it can move east
	// Returns true if it can, false if it can not
	public final boolean canMoveEast(int amount) {
		return canMoveHelper(0, amount);
	}

	// Checks to see if it can move west
	// Returns true if it can, false if it can not
	public final boolean canMoveWest(int amount) {
		return canMoveHelper(0, amount * -1);
	}

	private final boolean canMoveHelper(int amountN, int amountE) {
		int size = Nomads.awesomeWorld.getWorldSize();
		if (getY() + amountN >= size || getY() + amountN < 0
				|| getX() + amountE >= size || getX() + amountE < 0) {
			if (Nomads.DEBUGMOVES)
				System.out.println("Cannot move, out of range");
			return false;
		} else if (Nomads.awesomeWorld.getObjectAt(getX() + amountE, getY()
				+ amountN) != null) {
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

	// Returns how many turns this drone has been alive
	public final int getAge() {
		return age;
	}

	// Probably will not be needed.
	// TODO - find a way to prevent the drone from accessing this
	public final void setAge(int newAge) {
		age = newAge;
	}

	// Increases the age by 1 turn
	public final void incrementAge() {
		age++;
	}
}
