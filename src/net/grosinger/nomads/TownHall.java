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
				// TODO - Implement turning in objective
			}
		}
	}
}
