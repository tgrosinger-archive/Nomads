package net.grosinger.nomads;

import net.grosinger.nomads.DroneListItem.EnumMove;

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

	// Do not change these
	private String name;
	private String UID;

	// Define any variables that you need
	private DroneTools tools;

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
}
