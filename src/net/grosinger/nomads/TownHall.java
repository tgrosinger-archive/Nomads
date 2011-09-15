package net.grosinger.nomads;

import net.grosinger.nomads.exceptions.ObjectReferenceOutdated;

/**
 * A representation of a TownHall. Allows Drones to interact with this building.
 */
public class TownHall extends NeighborBuilding {

	public TownHall(int x, int y, String name, Building building, DroneListItem drone) {
		super(x, y, name, building, drone);
	}

	/**
	 * Will deposit all money piles and turn in all Objectives to Town Hall.
	 * 
	 * @throws ObjectReferenceOutdated
	 */
	public void cashInventory() throws ObjectReferenceOutdated {
		if (verifyObjectValidity()) {
			Inventory inventory = drone.getInventory();
			DroneTeam team = drone.getTeam();

			while (!inventory.isEmpty()) {
				GameObject currentObject = inventory.getItem(0);
				if (currentObject instanceof MoneyPile) {
					int value = ((MoneyPile) currentObject).getValue();
					team.increaseBalance(value);
					Nomads.awesomeWorld.generateMoneyPile();
				} else if (currentObject instanceof Objective) {
					team.increaseBalance(((Objective) currentObject).getBounty());
				}
				inventory.remove(currentObject);
			}
		} else {
			throw new ObjectReferenceOutdated();
		}
	}

	/**
	 * Will generate a new objective for the drone that requests it.
	 * 
	 * @return <code>Point</code> Will return null if the NeighborBuilding was
	 *         created in a previous turn.
	 * 
	 * @throws ObjectReferenceOutdated
	 */
	public Point requestNewObjective() throws ObjectReferenceOutdated {
		if (verifyObjectValidity()) {
			return Nomads.awesomeWorld.generateObjective(drone);
		} else {
			throw new ObjectReferenceOutdated();
		}
	}
}
