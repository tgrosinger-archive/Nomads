package net.grosinger.nomads.buildings;

import net.grosinger.nomads.drones.DroneListItem;

/**
 * A representation of a PoliceStation. Allows Drones to interact with this
 * building.
 */
public class PoliceStation extends NeighborBuilding {

	public PoliceStation(int x, int y, String name, Building building, DroneListItem drone) {
		super(x, y, name, building, drone);
	}

	// TODO - Implement Police Station

}
