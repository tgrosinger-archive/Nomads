package net.grosinger.nomads;

/*
 * Anything that is on the theWorld that is not a drone of some sort is a building
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
