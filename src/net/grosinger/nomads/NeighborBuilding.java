package net.grosinger.nomads;

/**
 * An array of NeigborBuildings will be given to a drone that is searching for
 * the buildings it is near. This is typically done from the town center.
 */
public class NeighborBuilding implements GameObject {

	private String name;
	private int turnCreated;
	private int x;
	private int y;
	private Building building;
	private DroneListItem drone;

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
	public NeighborBuilding(int x, int y, String name, Building building, DroneListItem drone) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.building = building;
		this.drone = drone;
		turnCreated = Nomads.turn;
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

	/**
	 * Checks to make sure the object was created this turn. Otherwise drones
	 * could save a reference to the object and use it from anywhere on the map.
	 * 
	 * @return <code>boolean</code>
	 */
	protected boolean verifyObjectValidity() {
		int currentTurn = Nomads.turn;
		return currentTurn == turnCreated;
	}

}
