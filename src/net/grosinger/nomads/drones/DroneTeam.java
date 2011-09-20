package net.grosinger.nomads.drones;

import java.util.ArrayList;

import net.grosinger.nomads.GameObject;
import net.grosinger.nomads.InitializeGame;
import net.grosinger.nomads.Nomads;
import net.grosinger.nomads.Point;
import net.grosinger.nomads.buildings.House;


/**
 * Contains a pointer to the first and the last DroneListItem in a particular
 * team. When adding drones to Team A they should be added to the end of the
 * DroneTeam So similar drones are grouped together
 */
public class DroneTeam {

	/**
	 * The first DroneListItem in the team
	 */
	private DroneListItem first;

	/**
	 * The last DroneListItem in the team
	 */
	private DroneListItem last;

	/**
	 * Taken from the first drone added to the team
	 */
	private String teamName;

	/**
	 * Amount of Lumas this team has accrued
	 */
	private int currentBalance;

	/**
	 * ArrayList of all the houses owned by this team;
	 */
	private ArrayList<House> teamHouses;

	/**
	 * Class Constructor
	 * 
	 * @param firstList
	 *            <code>DroneListItem</code> that will be the first Drone
	 */
	public DroneTeam(DroneListItem firstList) {
		first = firstList;
		last = firstList;
		finishConstructing();
	}

	/**
	 * Class Constructor
	 * 
	 * @param firstDrone
	 *            <code>GameObject</code> that will be the first Drone
	 */
	public DroneTeam(GameObject firstDrone) {
		DroneListItem firstList = new DroneListItem(null, null, (Drone) firstDrone, this);
		first = firstList;
		last = firstList;
		finishConstructing();
	}

	/**
	 * A few things that both constructors need, put into a different method.
	 */
	private void finishConstructing() {
		teamName = first.getCurrent().getName();
		currentBalance = 0;
		teamHouses = new ArrayList<House>();
	}

	// Getters and Setters

	/**
	 * Retrieves the first DroneListItem
	 * 
	 * @return First <code>DroneListItem</code>
	 */
	public DroneListItem getFirst() {
		return first;
	}

	/**
	 * Retrieves the last DroneListItem
	 * 
	 * @return Last <code>DroneListItem</code>
	 */
	public DroneListItem getLast() {
		return last;
	}

	/**
	 * Retrieves the Team Name
	 * 
	 * @return Team Name in a <code>String</code>
	 */
	public String getName() {
		return teamName;
	}

	/**
	 * Retrieves the balance for the team
	 * 
	 * @return Number of Lumas this team possesses
	 */
	public int getBalance() {
		return currentBalance;
	}

	/**
	 * Retrieve the ArrayList of all the houses this team owns
	 * 
	 * @return <code>ArrayList (House)</code>
	 */
	public ArrayList<House> getTeamHouses() {
		return teamHouses;
	}

	/**
	 * Sets the first DroneListItem to that provided
	 * 
	 * @param theFirst
	 *            <code>DroneListItem</code> to be made first
	 */
	public void setFirst(DroneListItem theFirst) {
		first = theFirst;
	}

	/**
	 * Sets the last DroneListItem to that provided
	 * 
	 * @param theLast
	 *            <code>DroneListItem</code> to be made last
	 */
	public void setLast(DroneListItem theLast) {
		last = theLast;
	}

	/**
	 * Sets the Team Name
	 * 
	 * @param newName
	 *            <String> to set the name to
	 */
	public void setName(String newName) {
		teamName = newName;
	}

	/**
	 * Replaces old balance with the new balance
	 * 
	 * @param newBalance
	 *            Amount to set the balance to
	 */
	public void setBalance(int newBalance) {
		currentBalance = newBalance;
	}

	/**
	 * Increases the balance by specified amount
	 * 
	 * @param additionalBalance
	 *            How much to add to the team's balance
	 */
	public void increaseBalance(int additionalBalance) {
		currentBalance += additionalBalance;
	}

	/**
	 * Adds a DroneListItem to the end of a DroneTeam
	 * 
	 * @param newItem
	 *            <code>DroneListItem</code> to add to end
	 */
	public void addToEnd(DroneListItem newItem) {
		last.setNext(newItem);
		newItem.setPrevious(last);
		newItem.setNext(null);
		last = newItem;
	}

	/**
	 * Always remove a DroneListItem through the DroneTeam so that it can tell
	 * if it needs to update the first or last pointers
	 * 
	 * @param toRemove
	 *            <code>DroneListItem</code> that needs to be removed
	 */
	public void removeDrone(DroneListItem toRemove) {
		DroneListItem firstDrone = first;
		while (firstDrone != null) {
			if (firstDrone.getX() == toRemove.getX() && firstDrone.getY() == toRemove.getY()) {
				// Make other two drones pass over it
				firstDrone.getPrevious().setNext(firstDrone.getNext());
				firstDrone.getNext().setPrevious(firstDrone.getPrevious());
				break;
			} else
				firstDrone = firstDrone.getNext();
		}
	}

	/**
	 * Asks each Drone in the team to take it's turn
	 */
	public void getMoves() {
		DroneListItem current = first;
		while (current != null) {
			// Increment the drone's age
			current.incrementAge();
			// Have it perform it's moves
			int turns = current.getSpeed();
			int currentTurn = 1;
			int currentTry = 1;
			while (currentTurn <= turns) {
				boolean moved = current.makeMove();
				if (moved)
					currentTurn++;
				else
					currentTry++;

				// Give the drone 3 tries to make a valid move before skipping
				// them
				if (currentTry > 3)
					break;
			}
			current = current.getNext();
		}
	}

	/**
	 * Removes money from the balance
	 * 
	 * @param price
	 *            - Amount to remove
	 */
	public void deductFromBalance(int price) {
		currentBalance -= price;
	}

	/**
	 * Creates a new drone on the same team next to the creator
	 * 
	 * @param listItem
	 *            - Drone that issued the request
	 * @param location
	 */
	public void createNewDrone(DroneListItem listItem, Point location) {
		Class<? extends Drone> c = listItem.getCurrent().getClass();
		Drone newDrone = null;
		try {
			newDrone = (Drone) c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		newDrone.setName(getName());
		newDrone.setUID(InitializeGame.generateUID());

		DroneListItem newListItem = new DroneListItem(null, last, newDrone, this);
		addToEnd(newListItem);

		newListItem.setX(location.getX());
		newListItem.setY(location.getY());

		Nomads.awesomeWorld.setObjectAt(location.getX(), location.getY(), newDrone);
	}
}
