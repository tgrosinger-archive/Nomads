package net.grosinger.nomads;

/**
 * An array of Neighbors will be given to a drone that is using it's radar
 */
public class Neighbor implements GameObject {

	private String name;
	private int x;
	private int y;

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
	public Neighbor(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
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

	@Override
	public void setName(String newName) {
		name = newName;
	}
}
