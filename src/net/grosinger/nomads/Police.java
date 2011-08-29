package net.grosinger.nomads;

import net.grosinger.nomads.DroneListItem.EnumMove;

/**
 * Enforcer of the law.  Has the ability to arrest drones that are wanted.
 * Police have a move speed of 2 squares/turn and get faster each time they catch a criminal.
 * Captured money and objects are turned into money which funds the police department.
 * 
 * Police have a visible distance of 2X BASE_VISIBLEDISTANCE
 * 
 */
public class Police implements Drone{
	// TODO - Implement Police

	private static final boolean DEBUGGINGALL = true;

	//Do not change these
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
		return null;
	}

	@Override
	public Neighbor steal() {
		return null;
	}

	@Override
	public Neighbor attack() {
		return null;
	}

	@Override
	public Upgrade upgrade() {
		return null;
	}
}
