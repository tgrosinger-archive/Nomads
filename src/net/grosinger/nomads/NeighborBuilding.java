package net.grosinger.nomads;

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
	 * Retrieve the name of the team that owns the current building. If the
	 * building is a public building, the string will be "Public".
	 * 
	 * @return <code>String</code>
	 */
	public String getTeam() {
		return building.getTeam();
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

}
