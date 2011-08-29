package net.grosinger.nomads;

public class Upgrade {

	public enum UpgradeType {
		visibleDistance, lumaLocatorDistance, objectLocatorDistance, reliability, attack, defenses, speed, cargoSpace, theft
	}

	private UpgradeType typeToUpgrade;

	public Upgrade(UpgradeType type) {
		typeToUpgrade = type;
	}

	/**
	 * Provides the prices for a given update type
	 * Algorithm: (((Current Level - Original Level) + 5) * Multiplier)^2
	 * 
	 * @return <code>int</code>
	 */
	public int getPrice() {
		switch (typeToUpgrade) {
		case visibleDistance: {
			
		}
		case lumaLocatorDistance: {

		}
		case objectLocatorDistance: {

		}
		case reliability: {

		}
		case attack: {

		}
		case defenses: {

		}
		case speed: {

		}
		case cargoSpace: {

		}
		case theft: {

		}
		default: {
			return (Integer) null;
		}
		}
	}
}
