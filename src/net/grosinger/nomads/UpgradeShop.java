package net.grosinger.nomads;

import net.grosinger.nomads.exceptions.InsufficientFundsException;
import net.grosinger.nomads.exceptions.InvalidUpgradeException;

/**
 * A representation of an UpgradeShop. Allows Drones to interact with this
 * building.
 */
public class UpgradeShop extends NeighborBuilding {

	public UpgradeShop(int x, int y, String name, Building building, DroneListItem drone) {
		super(x, y, name, building, drone);
	}

	/**
	 * Performs an upgrade on the drone if it is possible. Must be passed an
	 * upgrade that is not already of max level, and that has enough funds to
	 * purchase.
	 * 
	 * @param toPerform
	 *            An Upgrade that should be performed
	 * @throws InsufficientFundsException
	 * @throws InvalidUpgradeException
	 */
	public void performUpgrade(Upgrade toPerform) throws InsufficientFundsException,
			InvalidUpgradeException {
		int teamBalance = drone.getTeam().getBalance();
		int upgradeCost = toPerform.getPrice();

		if (upgradeCost > teamBalance) {
			throw new InsufficientFundsException();
		} else if (toPerform.isMaxLevel()) {
			throw new InvalidUpgradeException("Maximum level already achieved");
		} else {
			drone.incrementLevel(toPerform.getUpgradeType());
			drone.getTeam().deductFromBalance(upgradeCost);
		}
	}

}
