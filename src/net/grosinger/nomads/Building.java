package net.grosinger.nomads;

/*
 * Anything that is on the theWorld that is not a drone of some sort is a building
 * 
 * //Safe Zones - Measured in radius of a square
 * TownHall - 3
 * RepairShop - 2 
 * UpgradeShop - 2
 * PoliceStation - 3
 * Home - 1
 * 
 * 
 * 
 */
public class Building implements GameObject {
	Structure structure;
	String name;
	DroneTeam team;

	int x;
	int y;

	// Houses will extend Buildings
	public enum Structure {
		TOWNHALL, REPAIRSHOP, UPGRADESHOP, POLICESTATION, HOUSE
	}

	public Building(Structure thisBuilding, int newX, int newY, DroneTeam team) {
		structure = thisBuilding;
		switch (structure) {
		case TOWNHALL: {
			name = "TownHall";
			break;
		}
		case REPAIRSHOP: {
			name = "RepairShop";
			break;
		}
		case UPGRADESHOP: {
			name = "UpgradeShop";
			break;
		}
		case POLICESTATION: {
			name = "PoliceStation";
			break;
		}
		case HOUSE: {
			name = "House";
			break;
		}
		}

		this.team = team;
		x = newX;
		y = newY;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Retrieve what type of building it is (I.E. TownHall, RepairShop, etc.)
	 * 
	 * @return <code>Structure</code>
	 */
	public Structure getType() {
		return structure;
	}

	/**
	 * Retrieve the name of the team that owns the current building. If the
	 * building is a public building, the string will be "Public".
	 * 
	 * @return <code>String</code>
	 */
	public String getTeam() {
		if (structure == Structure.HOUSE) {
			return team.getName();
		} else {
			return "Public";
		}
	}

	/**
	 * Retrieve x index
	 * 
	 * return <code>int</code>
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retrieve y index
	 * 
	 * @return <code>int</code>
	 */
	public int getY() {
		return y;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Sets a new x value for the building (As if buildings could move)
	 * 
	 * @param newX
	 *            - int, new x location
	 */
	public void setX(int newX) {
		x = newX;
	}

	/**
	 * Sets a new y value for the building (As if buildings could move)
	 * 
	 * @param newY
	 *            - int, new y location
	 */
	public void setY(int newY) {
		y = newY;
	}

	/*
	 * Information about how to use the switch command with enums
	 * http://download.oracle.com/javase/tutorial/java/javaOO/enum.html
	 */
}
