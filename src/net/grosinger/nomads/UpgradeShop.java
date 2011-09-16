package net.grosinger.nomads;

import net.grosinger.nomads.exceptions.InsufficientFundsException;

/**
 * A representation of an UpgradeShop. Allows Drones to interact with this
 * building.
 */
public class UpgradeShop extends NeighborBuilding {

	public UpgradeShop(int x, int y, String name, Building building, DroneListItem drone) {
		super(x, y, name, building, drone);
	}

	// TODO - Implement Upgrade Shop

	/**
	 * Performs an upgrade on the drone if it is possible. Must be passed an
	 * upgrade that is not already of max level, and that has enough funds to
	 * purchase.
	 * 
	 * @param toPerform
	 *            An Upgrade that should be performed
	 * @throws InsufficientFundsException
	 */
	public void performUpgrade(Upgrade toPerform) throws InsufficientFundsException {
		int teamBalance = drone.getTeam().getBalance();
		int upgradeCost = toPerform.getPrice();

		if (upgradeCost > teamBalance) {
			throw new InsufficientFundsException();
		}
	}

}
