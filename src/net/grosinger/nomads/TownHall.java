package net.grosinger.nomads;

import java.util.ArrayList;

/**
 * A representation of a TownHall. Allows Drones to interact with this building.
 */
public class TownHall extends NeighborBuilding {

	public TownHall(int x, int y, String name, Building building) {
		super(x, y, name, building);
	}

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

	public Point requestNewObjective(String UID) {
		return Nomads.awesomeWorld.generateObjective(UID);
	}
}
