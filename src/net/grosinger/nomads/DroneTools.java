package net.grosinger.nomads;

/**
 * Tools for the Drone to use. Only place methods in here that you want the
 * drone to have access to.
 */
public class DroneTools {

	private Drone referredDrone;
	private DroneListItem listItem;
	private int worldSize;

	public DroneTools(Drone aDrone, DroneListItem droneParent) {
		referredDrone = aDrone;
		listItem = droneParent;
		worldSize = Nomads.awesomeWorld.getWorldSize();
	}

	/**
	 * Returns the X value of your Drone
	 * 
	 * @return <code>int</code> - X value
	 */
	public int getX() {
		return listItem.getX();
	}

	/**
	 * Returns the Y value of your Drone
	 * 
	 * @return <code>int</code> - Y value
	 */
	public int getY() {
		return listItem.getY();
	}

	/**
	 * Returns the age of this Drone
	 * 
	 * @return <code>int</code> - Age
	 */
	public int getAge() {
		return listItem.getAge();
	}

	// Movement

	/**
	 * Can drone move 1 space to the North
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveNorth() {
		if (getY() < worldSize-1)
			return Nomads.awesomeWorld.getWorldGrid()[getX()][getY() + 1] == null;
		else
			return false;
	}

	/**
	 * Can drone move 1 space to the South
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveSouth() {
		if (getY() > 0)
			return Nomads.awesomeWorld.getWorldGrid()[getX()][getY() - 1] == null;
		else
			return false;
	}

	/**
	 * Can drone move 1 space to the East
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveEast() {
		if (getX() < worldSize-1)
			return Nomads.awesomeWorld.getWorldGrid()[getX() + 1][getY()] == null;
		else
			return false;
	}

	/**
	 * Can drone move 1 space to the West
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveWest() {
		if (getX() > 0)
			return Nomads.awesomeWorld.getWorldGrid()[getX() - 1][getY()] == null;
		else
			return false;
	}

	/**
	 * Is the drone in an sort of safe zone where it can not be attacked. This
	 * also means the drone can not attack other drones.
	 * 
	 * @return <code>Boolean</code> - is Safe
	 */
	public boolean inSafeZone() {
		return Nomads.awesomeWorld.inSafeZone(getX(), getY());
	}
}
