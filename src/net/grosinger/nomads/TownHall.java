package net.grosinger.nomads;

import java.util.ArrayList;

/**
 * A representation of a TownHall. Allows Drones to interact with this building.
 */
public class TownHall extends NeighborBuilding {

	// TODO - Rewrite class to make more accessible to Drones

	public TownHall(int x, int y, String name, Building building) {
		super(x, y, name, building);
	}

	/**
	 * Will sell all items in inventory that are able to be sold to the Town
	 * Hall.
	 * 
	 * @param inventory
	 * @param team
	 */
	public void cashInventory(ArrayList<GameObject> inventory, DroneTeam team) {
		while (!inventory.isEmpty()) {
			GameObject currentObject = inventory.get(0);
			if (currentObject instanceof MoneyPile) {
				int value = ((MoneyPile) currentObject).getValue();
				team.increaseBalance(value);
				Nomads.awesomeWorld.generateMoneyPile();
			} else if (currentObject instanceof Objective) {
				team.increaseBalance(((Objective) currentObject).getBounty());
			}
			inventory.remove(currentObject);
		}
	}

	/**
	 * Will generate a new objective for the drone that requests it.
	 * 
	 * @param UID
	 *            Depreciated and going away soon.
	 * @return <code>Point</code> Will return null if the NeighborBuilding was
	 *         created in a previous turn.
	 */
	public Point requestNewObjective(String UID) {
		if (verifyObjectValidity()) {
			return Nomads.awesomeWorld.generateObjective(UID);
		} else {
			return null;
		}
	}
}
