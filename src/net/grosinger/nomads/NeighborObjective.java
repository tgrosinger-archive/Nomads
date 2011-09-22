package net.grosinger.nomads;

public class NeighborObjective implements GameObject {

	private String name;
	int x;
	int y;
	Objective reference;

	public NeighborObjective(int x, int y, Objective obj) {
		reference = obj;
		this.x = x;
		this.y = y;
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
	 * Retrieve bounty of this Objective
	 * 
	 * @return <code>int</code>
	 */
	public int getBounty() {
		return reference.getBounty();
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

}
