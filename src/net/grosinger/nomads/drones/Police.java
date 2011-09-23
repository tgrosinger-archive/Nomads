package net.grosinger.nomads.drones;

import net.grosinger.nomads.DroneTools;
import net.grosinger.nomads.Point;
import net.grosinger.nomads.drones.DroneListItem.EnumMove;

/**
 * Enforcer of the law. Has the ability to arrest drones that are wanted. Police
 * have a move speed of 2 squares/turn and get faster each time they catch a
 * criminal.
 * 
 * (Possible Idea) Captured money and objects are turned into money which funds
 * the police department.
 * 
 * Police have a visible distance of 2X BASE_VISIBLEDISTANCE
 * 
 */
public class Police implements Drone {
	// TODO - Implement Police

	private static final boolean DEBUGGINGALL = true;

	// Do not change these //
	private String name;
	private String UID;

	// Define any variables that you need //
	private DroneTools tools;

	// Moving Related
	private EnumMove previousDirection;
	private int movesLeftInThisDirection;
	private static Point backupRequestedHere;

	private Point iAmGoingToGiveBackup;

	// Leave these methods alone, they are required //

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

	@Override
	public void setUID(String newUID) {
		UID = newUID;
	}

	@Override
	public String getUID() {
		return UID;
	}

	@Override
	public void setDroneTools(DroneTools newTools) {
		tools = newTools;
	}

	// Edit from here down //

	@Override
	public EnumMove move() {
		if (DEBUGGINGALL) {
			System.out.println("Policeman about to move");
		}

		if (iAmGoingToGiveBackup != null) {
			// If I am not at this point already, go there

			// If I am at that point, unset the variable and start looking for
			// people
		} else if (backupRequestedHere != null) {
			// Another policeman needs backup, go there
			EnumMove returnThis = goToPoint(backupRequestedHere);

			// Remove the request for backup
			backupRequestedHere = null;
			return returnThis;
		} else if (movesLeftInThisDirection == 0) {
			// If I am not going anywhere specific, just pick a random
			// direction, travel that way for 3 moves, then repeat
			movesLeftInThisDirection = 2;
			previousDirection = pickRandomDirection();
			return previousDirection;
		} else if (movesLeftInThisDirection > 0) {
			// If I am not going anywhere specific, just pick a random
			// direction, travel that way for 3 moves, then repeat
			movesLeftInThisDirection--;
			return previousDirection;
		}

		// A police drone will move semi-randomly, looking for neighbors.
		// Upon finding neighbors it will scan them to see if any of them are
		// wanted. If one is wanted it will start to pursue them and attempt to
		// discern their direction of travel. Based on direction of travel it
		// will call in reinforcements that are in the area.
		// TODO - Implement Police Move
		return null;
	}

	@Override
	public NeighborDrone steal() {
		// This method is not used by the police drones
		return null;
	}

	@Override
	public NeighborDrone attack() {
		// When attacked, a police drone will automatically retaliate, dealing
		// 2x the damage that was done to itself.
		// TODO - Implement Police Attack
		return null;
	}

	public void requestBackup(Point goHere) {
		backupRequestedHere = goHere;
	}

	/**
	 * Chooses a random direction to travel from North, South, East, and West
	 * 
	 * @return <code>EnumMove</code>
	 */
	private EnumMove pickRandomDirection() {
		// TODO - Implement pickRandomDirection
		return EnumMove.South;
	}

	/**
	 * Will travel East/West then North/South to get to the point provided
	 * 
	 * @param goHere
	 *            <code>Point</code> that is to be traveled to
	 * @return <code>EnumMove</code> that is to be given to move method
	 */
	private EnumMove goToPoint(Point goHere) {
		int amountN = tools.getY() - goHere.getY();
		int amountE = tools.getX() - goHere.getX();

		if (DEBUGGINGALL) {
			System.out.println("Amount East: " + amountE + " Amount North: " + amountN);
		}

		// TODO - Find out why this isn't working
		if (amountE > 0 && tools.canMoveWest()) {
			if (DEBUGGINGALL) {
				System.out.println("TonysDrone moving West");
			}
			return EnumMove.West;
		} else if (amountE < 0 && tools.canMoveEast()) {
			if (DEBUGGINGALL) {
				System.out.println("TonysDrone moving East");
			}
			return EnumMove.East;
		} else if (amountN > 0 && tools.canMoveNorth()) {
			if (DEBUGGINGALL) {
				System.out.println("TonysDrone moving North");
			}
			return EnumMove.North;
		} else if (amountN < 0 && tools.canMoveSouth()) {
			if (DEBUGGINGALL) {
				System.out.println("TonysDrone moving South");
			}
			return EnumMove.South;
		} else {
			return EnumMove.NoMove;
		}
	}
}
