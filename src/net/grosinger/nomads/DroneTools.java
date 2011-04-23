package net.grosinger.nomads;

/**
 * Tools for the Drone to use. Only place methods in here that you want the
 * drone to have access to.
 */
public class DroneTools {

	private Drone referredDrone;
	private DroneListItem listItem;

	public DroneTools(Drone aDrone, DroneListItem droneParent) {
		referredDrone = aDrone;
		listItem = droneParent;
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

	public boolean canMoveNorth() {
		return Nomads.awesomeWorld.getWorldGrid()[getY() + 1][getX()] == null;
	}

	public boolean canMoveSouth() {
		return Nomads.awesomeWorld.getWorldGrid()[getY() - 1][getX()] == null;
	}

	public boolean canMoveEast() {
		return Nomads.awesomeWorld.getWorldGrid()[getY()][getX() + 1] == null;
	}

	public boolean canMoveWest() {
		return Nomads.awesomeWorld.getWorldGrid()[getY()][getX() - 1] == null;
	}
}
