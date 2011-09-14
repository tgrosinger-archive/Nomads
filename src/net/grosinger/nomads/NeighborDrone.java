package net.grosinger.nomads;


/**
 * An array of NeighborDrones will be given to a drone that is using it's radar
 */
public class NeighborDrone implements GameObject {

	private String name;
	private int x;
	private int y;
	private String UID;

	/**
	 * Class Constructor
	 * 
	 * @param x
	 *            - X location of the Drone
	 * @param y
	 *            - Y location of the Drone
	 * @param name
	 *            - Name of the Drone
	 */
	public NeighborDrone(int x, int y, String name, String UID) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.UID = UID;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Retrieve x location
	 * 
	 * @return <code>int</code> - x Location
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retrieve y location
	 * 
	 * @return <code>int</code> - y Location
	 */
	public int getY() {
		return y;
	}

	/**
	 * Retrieve UID of this Neighbor
	 * 
	 * @return <code>String</code> - UID
	 */
	public String getUID() {
		return UID;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}
}
