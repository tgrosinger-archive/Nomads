package net.grosinger.nomads;

import java.util.ArrayList;

public class TownHall extends Building {

	public TownHall(int newX, int newY) {
		super(Structure.TOWNHALL, newX, newY);
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
	
	public Point requestNewObjective(String UID){
		return Nomads.awesomeWorld.generateObjective(UID);
	}
}
