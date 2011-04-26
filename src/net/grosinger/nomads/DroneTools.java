package net.grosinger.nomads;

import java.util.ArrayList;

/**
 * Tools for the Drone to use. Only place methods in here that you want the
 * drone to have access to.
 */
public class DroneTools {

	private Drone referredDrone;
	private DroneListItem listItem;
	private World worldReference;
	private int worldSize;

	public DroneTools(Drone aDrone, DroneListItem droneParent, World theWorld) {
		referredDrone = aDrone;
		listItem = droneParent;
		worldReference = theWorld;
		worldSize = worldReference.getWorldSize();
	}

	/**
	 * Returns the X value of your Drone
	 * 
	 * @return <code>int</code> - X value
	 */
	public int getX() {
		return listItem.getX();
	}

	/**
	 * Returns the Y value of your Drone
	 * 
	 * @return <code>int</code> - Y value
	 */
	public int getY() {
		return listItem.getY();
	}

	/**
	 * Returns the age of this Drone
	 * 
	 * @return <code>int</code> - Age
	 */
	public int getAge() {
		return listItem.getAge();
	}

	// Movement

	/**
	 * Can drone move 1 space to the North
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveNorth() {
		if (getY() < worldSize-1)
			return worldReference.getWorldGrid()[getX()][getY() + 1] == null;
		else
			return false;
	}

	/**
	 * Can drone move 1 space to the South
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveSouth() {
		if (getY() > 0)
			return worldReference.getWorldGrid()[getX()][getY() - 1] == null;
		else
			return false;
	}

	/**
	 * Can drone move 1 space to the East
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveEast() {
		if (getX() < worldSize-1)
			return worldReference.getWorldGrid()[getX() + 1][getY()] == null;
		else
			return false;
	}

	/**
	 * Can drone move 1 space to the West
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveWest() {
		if (getX() > 0)
			return worldReference.getWorldGrid()[getX() - 1][getY()] == null;
		else
			return false;
	}

	/**
	 * Is the drone in an sort of safe zone where it can not be attacked. This
	 * also means the drone can not attack other drones.
	 * 
	 * @return <code>Boolean</code> - is Safe
	 */
	public boolean inSafeZone() {
		return worldReference.inSafeZone(getX(), getY());
	}
	
	/**
	 * Retrieve a list of all Drones that are visible within your sight range.  (Sight range can be upgraded)
	 * 
	 * @return ArrayList of Neighbors
	 */
	public ArrayList<Neighbor> checkRadar(){
		ArrayList<Neighbor> neighbors = new ArrayList<Neighbor>();
		int maxDistance = listItem.getVisibleDistance();
		for(int i = maxDistance * -1; i <= maxDistance; i++){
			for(int j = maxDistance * -1; j <= maxDistance; j++){
				if (getX() + i >= worldSize-1 || getX() + i < 0 || getY() + j >= worldSize-1 || getY() + j < 0) {

				} else if(i != 0 && j != 0) {
					GameObject objectHere = worldReference.getObjectAt(getX()+i, getY()+j);
					if(objectHere instanceof Drone){ //TODO - Not sure if this will work
						Drone droneHere = (Drone) objectHere;
						DroneListItem listItemHere = Nomads.droneToListItem(droneHere);
						Neighbor aWildNeighbor = new Neighbor(listItemHere.getX(), listItemHere.getY(), droneHere.getName());
						neighbors.add(aWildNeighbor);
					}
				}
			}
		}
		return neighbors;
	}
}
