package net.grosinger.nomads;

import net.grosinger.nomads.DroneListItem.EnumMove;

/**
 * All drones will extend this class. Gives them access to basic set of tools
 * they need such as movement, discovery, and world interaction.
 */
public interface Drone extends GameObject {

	/**
	 * This will give you a set of tools that will provide you will all the
	 * basic info your drone needs.
	 */
	public void setDroneTools(DroneTools yourTools);

	// Moving
	// When moving, North is positive Y axis and East is positive X axis

	/**
	 * The main move method for all Drones. Custom Drones should override this
	 * with their own.
	 */
	public EnumMove move();

}
