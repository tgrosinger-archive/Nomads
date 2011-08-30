package net.grosinger.nomads;

public class Upgrade {

	public enum UpgradeType {
		visibleDistance, lumaLocatorDistance, objectLocatorDistance, reliability, attack, defenses, speed, cargoSpace, theft
	}

	/**
	 * The upgrade that is to be performed.
	 */
	private UpgradeType typeToUpgrade;

	/**
	 * Drone to be upgraded. Used to find the current levels of this drone.
	 */
	private DroneListItem oldBrokenDrone;

	public Upgrade(UpgradeType type) {
		typeToUpgrade = type;
	}

	/**
	 * Provides the prices for a given update type Algorithm: (((Current Level -
	 * Original Level) + 5) * Multiplier)^2
	 * 
	 * @return <code>int</code>
	 */
	public int getPrice() {
		double multiplier;
		double currentLevel;
		double originalLevel;

		switch (typeToUpgrade) {
		case visibleDistance: {
			multiplier = 2;
			currentLevel = oldBrokenDrone.getVisibleDistance();
			originalLevel = Nomads.BASE_VISIBLEDISTANCE;
			break;
		}
		case lumaLocatorDistance: {
			multiplier = 2.5;
			currentLevel = oldBrokenDrone.getLumaLocatorDistance();
			originalLevel = Nomads.BASE_LUMALOCATORDISTANCE;
			break;
		}
		case objectLocatorDistance: {
			multiplier = 2.5;
			currentLevel = oldBrokenDrone.getObjectLocatorDistance();
			originalLevel = Nomads.BASE_OBJECTLOCATORDISTANCE;
			break;
		}
		case reliability: {
			multiplier = 2.4;
			currentLevel = oldBrokenDrone.getReliability();
			originalLevel = Nomads.BASE_RELIABILITY;
			break;
		}
		case attack: {
			multiplier = 2.7;
			currentLevel = oldBrokenDrone.getAttack();
			originalLevel = Nomads.BASE_ATTACK;
			break;
		}
		case defenses: {
			multiplier = 2.8;
			currentLevel = oldBrokenDrone.getDefenses();
			originalLevel = Nomads.BASE_DEFENSES;
			break;
		}
		case speed: {
			multiplier = 3.5;
			currentLevel = oldBrokenDrone.getSpeed();
			originalLevel = Nomads.BASE_SPEED;
			break;
		}
		case cargoSpace: {
			multiplier = 3.5;
			currentLevel = oldBrokenDrone.getCargoSpace();
			originalLevel = Nomads.BASE_CARGOSPACE;
			break;
		}
		case theft: {
			multiplier = 4;
			currentLevel = oldBrokenDrone.getTheft();
			originalLevel = Nomads.BASE_THEFT;
			break;
		}
		default: {
			// Must specify an Upgrade Type
			return (Integer) null;
		}
		}

		double price = Math.pow(
				(currentLevel - originalLevel + 5) * multiplier, 2.0);
		return (int) Math.ceil(price);
	}

	/**
	 * Determines if the statistic wanting to be upgraded is already at the
	 * maximum level.
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isMaxLevel() {
		// TODO - Implement max levels
		return false;
	}

	public UpgradeType getUpgradeType() {
		return typeToUpgrade;
	}
}
