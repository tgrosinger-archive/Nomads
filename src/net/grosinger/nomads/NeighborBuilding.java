package net.grosinger.nomads;

import net.grosinger.nomads.Building.Structure;

/**
 * An array of NeigborBuildings will be given to a drone that is searching for
 * the buildings it is near. This is typically done from the town center.
 */
public class NeighborBuilding implements GameObject {

	private String name;
	private int x;
	private int y;
	private Building building;

	/**
	 * Class Constructor
	 * 
	 * @param x
	 *            - X location of the Building
	 * @param y
	 *            - Y location of the Building
	 * @param name
	 *            - Name of the Building
	 */
	public NeighborBuilding(int x, int y, String name, Building building) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.building = building;
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
	 * Retrieve type of building that this is
	 * 
	 * @return <code>Structure</code>
	 */
	public Structure getType() {
		return building.getType();
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

}
