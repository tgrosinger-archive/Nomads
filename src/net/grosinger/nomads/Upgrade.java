package net.grosinger.nomads;

public class Upgrade {
	
	public enum UpgradeType {
		visibleDistance, lumaLocatorDistance, objectLocatorDistance, reliability, attack, defenses, speed, cargoSpace, theft
	}
	
	private UpgradeType typeToUpgrade;
	
	public Upgrade(UpgradeType type){
		typeToUpgrade = type;
	}
}
