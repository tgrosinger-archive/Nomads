package net.grosinger.nomads.buildings;

import net.grosinger.nomads.drones.DroneListItem;

/**
 * A representation of a RepairShop. Allows Drones to interact with this
 * building.
 */
public class RepairShop extends NeighborBuilding {

	public RepairShop(int x, int y, String name, Building building, DroneListItem drone) {
		super(x, y, name, building, drone);
	}

	// TODO - Implement Repair Shop

}
