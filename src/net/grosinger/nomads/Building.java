package net.grosinger.nomads;

/*
 * Anything that is on the theWorld that is not a drone of some sort is a building
 */
public class Building extends GameObject {
	Structure structure;

	public enum Structure {
		TOWNHALL, REPAIRSHOP, UPGRADESHOP, POLICESTATION
	}

	public Building(Structure thisBuilding) {
		structure = thisBuilding;
	}

	/*
	 * Information about how to use the switch command with enums
	 * http://download.oracle.com/javase/tutorial/java/javaOO/enum.html
	 */
}
