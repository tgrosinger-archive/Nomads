package net.grosinger.nomads.drones;

import net.grosinger.nomads.DroneTools;
import net.grosinger.nomads.GameObject;
import net.grosinger.nomads.drones.DroneListItem.EnumMove;

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

	/**
	 * A Unique Identifier
	 * 
	 * @param UID
	 */
	public void setUID(String UID);

	/**
	 * Retrieve UID
	 * 
	 * @return <code>String</code> - UID
	 */
	public String getUID();

	// Moving
	// When moving, North is positive Y axis and East is positive X axis

	/**
	 * The main move method for all Drones. Custom Drones should override this
	 * with their own.
	 */
	public EnumMove move();

	/**
	 * Asks for which drone should be stolen from. Will be called whenever the
	 * Move method returns Steal.
	 * 
	 * @return <code>Neighbor</code>;
	 */
	public NeighborDrone steal();

	/**
	 * Asks for which drone should be attacked. Will be called whenever the Move
	 * method returns Attack.
	 * 
	 * @return <code>Neighbor</code>
	 */
	public NeighborDrone attack();

}
