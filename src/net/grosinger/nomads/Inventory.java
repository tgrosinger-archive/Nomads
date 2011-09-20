package net.grosinger.nomads;

import java.util.ArrayList;

import net.grosinger.nomads.drones.DroneListItem;
import net.grosinger.nomads.exceptions.FullInventoryException;

public class Inventory {
	private DroneListItem droneItem;
	private ArrayList<GameObject> allItems;
	private int maxSize;

	public Inventory(DroneListItem drone) {
		droneItem = drone;
		allItems = new ArrayList<GameObject>();
		updateMaxSize();
	}

	/**
	 * Remove a specific GameObject from the inventory
	 * 
	 * @param obj
	 *            Object to be removed
	 */
	public void remove(GameObject obj) {
		allItems.remove(obj);
	}

	/**
	 * Remove the GameObject at the specified index from the inventory
	 * 
	 * @param index
	 *            Index to be removed
	 */
	public void remove(int index) {
		allItems.remove(index);
	}

	/**
	 * Get the GameObject that is at the specified index and return
	 * 
	 * @param index
	 *            Index to be found
	 * @return <code>GameObject</code>
	 */
	public GameObject getItem(int index) {
		return allItems.get(index);
	}

	/**
	 * Add an item to the inventory
	 * 
	 * @param item
	 *            Item to be added
	 * @throws FullInventoryException
	 */
	public void addItem(GameObject item) throws FullInventoryException {
		updateMaxSize();

		if (allItems.size() >= maxSize) {
			throw new FullInventoryException();
		} else {
			allItems.add(item);
		}
	}

	/**
	 * Determine if the inventory is completely empty of all GameObjects
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isEmpty() {
		return allItems.isEmpty();
	}

	/**
	 * Determine if the inventory is full or not and return
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isFull() {
		updateMaxSize();
		return allItems.size() >= maxSize;
	}

	public int size() {
		return allItems.size();
	}

	private void updateMaxSize() {
		maxSize = droneItem.getCargoSpace();
	}
}
