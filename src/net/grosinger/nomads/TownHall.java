package net.grosinger.nomads;

import net.grosinger.nomads.exceptions.BadRequestException;
import net.grosinger.nomads.exceptions.ObjectReferenceOutdatedException;

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
	 * @throws ObjectReferenceOutdatedException
	 */
	public void cashInventory() throws ObjectReferenceOutdatedException {
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
			throw new ObjectReferenceOutdatedException();
		}
	}

	/**
	 * Will generate a new objective for the drone that requests it.
	 * 
	 * @return <code>Point</code> Will return null if the NeighborBuilding was
	 *         created in a previous turn.
	 * 
	 * @throws ObjectReferenceOutdatedException
	 * @throws BadRequestException
	 */
	public Point requestNewObjective() throws ObjectReferenceOutdatedException, BadRequestException {
		if (verifyObjectValidity()) {
			if (drone.getCurrentObjectivesFull()) {
				throw new BadRequestException(
						"Maximum number of requested objectives already achieved");
			}
			return Nomads.awesomeWorld.generateObjective(drone);
		} else {
			throw new ObjectReferenceOutdatedException();
		}
	}
}
