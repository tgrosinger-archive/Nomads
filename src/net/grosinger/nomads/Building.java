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

	// Can't decide if I want to add Drone Houses as another enum item or as a
	// separate class that extends Building
	public enum Structure {
		TOWNHALL, REPAIRSHOP, UPGRADESHOP, POLICESTATION
	}

	public Building(Structure thisBuilding) {
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
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

	/*
	 * Information about how to use the switch command with enums
	 * http://download.oracle.com/javase/tutorial/java/javaOO/enum.html
	 */
}
