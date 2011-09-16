package net.grosinger.nomads;

import java.util.Hashtable;

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
	 * Retrieve the type of the upgrade that should be performed.
	 * 
	 * @return <code>UpgradeType</code>
	 */
	public UpgradeType getUpgradeType() {
		return typeToUpgrade;
	}

	/**
	 * Provides the prices for a given update type Algorithm: (((Current Level -
	 * Original Level) + 5) * Multiplier)^2
	 * 
	 * @return <code>int</code>
	 */
	public int getPrice() {
		Hashtable<String, Double> allInfo = statInfo(typeToUpgrade);

		double multiplier = allInfo.get("multiplier");
		double currentLevel = allInfo.get("currentLevel");
		double originalLevel = allInfo.get("originalLevel");

		double price = Math.pow((currentLevel - originalLevel + 5) * multiplier, 2.0);
		return (int) Math.ceil(price);
	}

	/**
	 * Determines if the statistic wanting to be upgraded is already at the
	 * maximum level.
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isMaxLevel() {
		Hashtable<String, Double> allInfo = statInfo(typeToUpgrade);

		double maxLevel = allInfo.get("maxLevel");
		double currentLevel = allInfo.get("currentLevel");

		return maxLevel == currentLevel;
	}

	/**
	 * Retrieve general information about a particular stat
	 * 
	 * @param type
	 *            The stat to generate information about
	 * @return <code>HashTable(string, double)</code>
	 */
	private Hashtable<String, Double> statInfo(UpgradeType type) {
		Hashtable<String, Double> allInfo = new Hashtable<String, Double>();

		switch (typeToUpgrade) {
		case visibleDistance: {
			allInfo.put("multiplier", (double) 2);
			allInfo.put("currentLevel", (double) oldBrokenDrone.getVisibleDistance());
			allInfo.put("originalLevel", (double) Nomads.BASE_VISIBLEDISTANCE);
			allInfo.put("maxLevel", (double) Nomads.MAXLEVEL_RADARS);
			break;
		}
		case lumaLocatorDistance: {

			allInfo.put("multiplier", (double) 2.5);
			allInfo.put("currentLevel", (double) oldBrokenDrone.getLumaLocatorDistance());
			allInfo.put("originalLevel", (double) Nomads.BASE_LUMALOCATORDISTANCE);
			allInfo.put("maxLevel", (double) Nomads.MAXLEVEL_RADARS);
			break;
		}
		case objectLocatorDistance: {
			allInfo.put("multiplier", (double) 2.5);
			allInfo.put("currentLevel", (double) oldBrokenDrone.getObjectLocatorDistance());
			allInfo.put("originalLevel", (double) Nomads.BASE_OBJECTLOCATORDISTANCE);
			allInfo.put("maxLevel", (double) Nomads.MAXLEVEL_RADARS);
			break;
		}
		case reliability: {
			allInfo.put("multiplier", (double) 2.4);
			allInfo.put("currentLevel", (double) oldBrokenDrone.getReliability());
			allInfo.put("originalLevel", (double) Nomads.BASE_RELIABILITY);
			allInfo.put("maxLevel", (double) Nomads.MAXLEVEL_STATS);
			break;
		}
		case attack: {
			allInfo.put("multiplier", (double) 2.7);
			allInfo.put("currentLevel", (double) oldBrokenDrone.getAttack());
			allInfo.put("originalLevel", (double) Nomads.BASE_ATTACK);
			allInfo.put("maxLevel", (double) Nomads.MAXLEVEL_STATS);
			break;
		}
		case defenses: {
			allInfo.put("multiplier", (double) 2.8);
			allInfo.put("currentLevel", (double) oldBrokenDrone.getDefenses());
			allInfo.put("originalLevel", (double) Nomads.BASE_DEFENSES);
			allInfo.put("maxLevel", (double) Nomads.MAXLEVEL_STATS);
			break;
		}
		case speed: {
			allInfo.put("multiplier", (double) 3.5);
			allInfo.put("currentLevel", (double) oldBrokenDrone.getSpeed());
			allInfo.put("originalLevel", (double) Nomads.BASE_SPEED);
			allInfo.put("maxLevel", (double) Nomads.MAXLEVEL_STATS);
			break;
		}
		case cargoSpace: {
			allInfo.put("multiplier", (double) 3.5);
			allInfo.put("currentLevel", (double) oldBrokenDrone.getCargoSpace());
			allInfo.put("originalLevel", (double) Nomads.BASE_CARGOSPACE);
			allInfo.put("maxLevel", (double) Nomads.MAXLEVEL_STATS);
			break;
		}
		case theft: {
			allInfo.put("multiplier", (double) 4);
			allInfo.put("currentLevel", (double) oldBrokenDrone.getTheft());
			allInfo.put("originalLevel", (double) Nomads.BASE_THEFT);
			allInfo.put("maxLevel", (double) Nomads.MAXLEVEL_STATS);
			break;
		}
		default: {
			// Must specify an Upgrade Type
			return null;
		}
		}

		return allInfo;
	}
}
