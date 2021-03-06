package net.grosinger.nomads;

import java.util.ArrayList;

import net.grosinger.nomads.buildings.Building;
import net.grosinger.nomads.buildings.House;
import net.grosinger.nomads.buildings.NeighborBuilding;
import net.grosinger.nomads.buildings.PoliceStation;
import net.grosinger.nomads.buildings.RepairShop;
import net.grosinger.nomads.buildings.TownHall;
import net.grosinger.nomads.buildings.UpgradeShop;
import net.grosinger.nomads.buildings.Building.Structure;
import net.grosinger.nomads.drones.Drone;
import net.grosinger.nomads.drones.DroneListItem;
import net.grosinger.nomads.drones.DroneTeam;
import net.grosinger.nomads.drones.NeighborDrone;

/**
 * Tools for the Drone to use. Only place methods in here that you want the
 * drone to have access to.
 */
public class DroneTools {

	private Drone referredDrone;
	private DroneListItem listItem;
	private DroneTeam currentTeam;
	private World worldReference;
	private Point townCenter;
	private int worldSize;

	public DroneTools(Drone aDrone, DroneListItem droneParent, World theWorld) {
		referredDrone = aDrone;
		listItem = droneParent;
		worldReference = theWorld;
		worldSize = worldReference.getWorldSize();
		currentTeam = listItem.getTeam();
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
		return canMoveHelper(getX(), getY() - 1);
	}

	/**
	 * Can drone move 1 space to the South
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveSouth() {
		return canMoveHelper(getX(), getY() + 1);
	}

	/**
	 * Can drone move 1 space to the East
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveEast() {
		return canMoveHelper(getX() + 1, getY());
	}

	/**
	 * Can drone move 1 space to the West
	 * 
	 * @return <code>Boolean</code> - can move
	 */
	public boolean canMoveWest() {
		return canMoveHelper(getX() - 1, getY());
	}

	/**
	 * Does actual checking to see if a space is able to be occupied
	 * 
	 * @param x
	 *            - X index
	 * @param y
	 *            - Y index
	 * @return <code>boolean</code>
	 */
	private boolean canMoveHelper(int x, int y) {
		// Account for being able to move onto MoneyPiles and Objectives
		// Check to make sure spot is on the map
		if (x >= 0 && y >= 0 && x <= worldSize - 1 && y <= worldSize - 1) {
			GameObject objectHere = worldReference.getWorld()[x][y];
			if (objectHere == null || objectHere instanceof MoneyPile
					|| objectHere instanceof Objective)
				return true;
			else
				return false;
		} else
			return false;
	}

	/**
	 * Is the drone in an sort of safe zone where it can not be attacked. This
	 * also means the drone can not attack other drones.
	 * 
	 * @return <code>Boolean</code> - is Safe
	 */
	public boolean inSafeZone() {
		return worldReference.inSafeZone(getX(), getY(), listItem);
	}

	/**
	 * Return the location of the center of the main town. Buildings are located
	 * on the corners of this point.
	 * 
	 * @return <code>Point</code>
	 */
	public Point getTownCenter() {
		if (townCenter == null) {
			townCenter = new Point(40, 50);
		}
		return townCenter;
	}

	/**
	 * Retrieve a list of all Buildings that are visible within your sight
	 * range. (Sight range can be upgraded)
	 * 
	 * @return <code>ArrayList</code> of NeigborBuildings
	 */
	public ArrayList<NeighborBuilding> checkBuildings() {
		ArrayList<NeighborBuilding> neighbors = new ArrayList<NeighborBuilding>();
		int maxDistance = listItem.getVisibleDistance();
		for (int i = maxDistance * -1; i <= maxDistance; i++) {
			for (int j = maxDistance * -1; j <= maxDistance; j++) {
				if (getX() + i >= worldSize - 1 || getX() + i < 0 || getY() + j >= worldSize - 1
						|| getY() + j < 0) {

				} else if (i != 0 && j != 0) {
					GameObject objectHere = worldReference.getObjectAt(getX() + i, getY() + j);
					if (objectHere instanceof Building) {
						Building buildingHere = (Building) objectHere;
						NeighborBuilding aWildNeighbor;

						if (buildingHere.getType() == Structure.TOWNHALL) {
							aWildNeighbor = new TownHall(buildingHere.getX(), buildingHere.getY(),
									buildingHere.getName(), buildingHere, listItem);
						} else if (buildingHere.getType() == Structure.REPAIRSHOP) {
							aWildNeighbor = new RepairShop(buildingHere.getX(),
									buildingHere.getY(), buildingHere.getName(), buildingHere,
									listItem);
						} else if (buildingHere.getType() == Structure.UPGRADESHOP) {
							aWildNeighbor = new UpgradeShop(buildingHere.getX(),
									buildingHere.getY(), buildingHere.getName(), buildingHere,
									listItem);
						} else if (buildingHere.getType() == Structure.POLICESTATION) {
							aWildNeighbor = new PoliceStation(buildingHere.getX(),
									buildingHere.getY(), buildingHere.getName(), buildingHere,
									listItem);
						} else {
							aWildNeighbor = new NeighborBuilding(buildingHere.getX(),
									buildingHere.getY(), buildingHere.getName(), buildingHere,
									listItem);
						}

						neighbors.add(aWildNeighbor);
					}
				}
			}
		}
		return neighbors;
	}

	/**
	 * Retrieve a list of all Drones that are visible within your sight range.
	 * (Sight range can be upgraded)
	 * 
	 * @return ArrayList of Neighbor Drones
	 */
	public ArrayList<NeighborDrone> checkRadar() {
		ArrayList<NeighborDrone> neighbors = new ArrayList<NeighborDrone>();
		int maxDistance = listItem.getVisibleDistance();
		for (int i = maxDistance * -1; i <= maxDistance; i++) {
			for (int j = maxDistance * -1; j <= maxDistance; j++) {
				if (getX() + i >= worldSize - 1 || getX() + i < 0 || getY() + j >= worldSize - 1
						|| getY() + j < 0) {

				} else if (i != 0 && j != 0) {
					GameObject objectHere = worldReference.getObjectAt(getX() + i, getY() + j);
					if (objectHere instanceof Drone) {
						Drone droneHere = (Drone) objectHere;
						DroneListItem listItemHere = Nomads.droneToListItem(droneHere);
						NeighborDrone aWildNeighbor = new NeighborDrone(listItemHere.getX(),
								listItemHere.getY(), droneHere.getName(), droneHere.getUID());
						neighbors.add(aWildNeighbor);
					}
				}
			}
		}
		return neighbors;
	}

	/**
	 * Retrieve a list of all MoneyPiles that are visible from this drone.
	 * Does not return the money pile itself, rather just a point where one
	 * lies.
	 * 
	 * @return ArrayList of Points
	 */
	public ArrayList<Point> checkLumaLocator() {
		ArrayList<Point> neighbors = new ArrayList<Point>();
		int maxDistance = listItem.getLumaLocatorDistance();
		for (int i = maxDistance * -1; i <= maxDistance; i++) {
			for (int j = maxDistance * -1; j <= maxDistance; j++) {
				if (getX() + i >= worldSize - 1 || getX() + i < 0 || getY() + j >= worldSize - 1
						|| getY() + j < 0) {

				} else if (i != 0 && j != 0) {
					GameObject objectHere = worldReference.getObjectAt(getX() + i, getY() + j);
					if (objectHere instanceof MoneyPile) {
						Point location = new Point(getX() + i, getY() + j);
						neighbors.add(location);
					}
				}
			}
		}
		return neighbors;
	}

	/**
	 * Retrieve a list of all objectives that are visible from this drone.
	 * Will tell if the objective belongs to this drone or not.
	 * 
	 * @return ArrayList of ObjectiveReferences
	 */
	public ArrayList<NeighborObjective> checkObjectiveLocator() {
		ArrayList<NeighborObjective> neighbors = new ArrayList<NeighborObjective>();
		int maxDistance = listItem.getObjectLocatorDistance();
		for (int i = maxDistance * -1; i <= maxDistance; i++) {
			for (int j = maxDistance * -1; j <= maxDistance; j++) {
				if (getX() + i >= worldSize - 1 || getX() + i < 0 || getY() + j >= worldSize - 1
						|| getY() + j < 0) {

				} else if (i != 0 && j != 0) {
					GameObject objectHere = worldReference.getObjectAt(getX() + i, getY() + j);
					if (objectHere instanceof Objective) {
						Objective objectiveHere = (Objective) objectHere;
						if (objectiveHere.getUID().equals(referredDrone.getUID())) {
							NeighborObjective reference = new NeighborObjective(getX() + i, getY()
									+ j, objectiveHere);
							neighbors.add(reference);
						}
					}
				}
			}
		}
		return neighbors;
	}

	/**
	 * If your team has enough money, will create a house 1 space east of
	 * current location.
	 * 
	 * @return Reference to the house so you can find it later. Will return null
	 *         if it can not create the house.
	 */
	public House createHouse() {
		if (hasEnoughCash(Nomads.HOUSEPRICE)) {
			// Find the closest spot to you that is open
			Point intendedPoint = new Point(getX(), getY());
			findEmptyPoint(intendedPoint);

			House newHouse = new House(Structure.HOUSE, intendedPoint.getX(), intendedPoint.getY(),
					currentTeam);
			worldReference.placeNewBuilding(newHouse);
			currentTeam.deductFromBalance(Nomads.HOUSEPRICE);
			listItem.setWaiting(Nomads.CREATIONTIME);
			return newHouse;
		} else
			return null;
	}

	/**
	 * If your team has enough money, will create an exact clone of your drone.
	 */
	public void createNewDrone() {
		if (hasEnoughCash(Nomads.DRONEPRICE)) {
			Point location = new Point(getX(), getY());
			currentTeam.createNewDrone(listItem, location);
			currentTeam.deductFromBalance(Nomads.DRONEPRICE);
			listItem.setWaiting(Nomads.CREATIONTIME);
		}
	}

	/**
	 * Find "closest" point that is available to the point provided
	 * 
	 * @param currentPoint
	 *            - Location of drone
	 * @return <code>Point</code>
	 */
	private Point findEmptyPoint(Point currentPoint) {
		// Current point is where the drone is
		boolean validSpace = worldReference.getObjectAt(currentPoint.getX(), currentPoint.getY()) == null;
		Point tryThis = new Point(currentPoint.getX(), currentPoint.getY());
		int outX = 1;
		int outY = 0;
		int multiplier = 1;

		while (!validSpace) {
			tryThis.setX(currentPoint.getX() + (outX * multiplier));
			tryThis.setY(currentPoint.getY() + (outY * multiplier));
			if (worldReference.getObjectAt(tryThis.getX(), tryThis.getY()) == null)
				validSpace = true;
			else {
				if (outX == 1 && outY == 0) {
					outY = 1;
				} else if (outX == 1 && outY == 1)
					outX = 0;
				else if (outX == 0 && outY == 1)
					outX = -1;
				else if (outX == -1 && outY == 1)
					outY = 0;
				else if (outX == -1 && outY == 0)
					outY = -1;
				else if (outX == -1 && outY == -1)
					outX = 0;
				else if (outX == 0 && outY == -1)
					outX = 1;
				else if (outX == 1 && outY == -1) {
					outY = 0;
					outX = 1;
					multiplier++;
				}
			}
		}
		return tryThis;
	}

	/**
	 * Tests to see if the team has enough money for an action
	 * 
	 * @param price
	 *            - Amount of money required
	 * @return <code>boolean</code>
	 */
	private boolean hasEnoughCash(int price) {
		int currentBalance = listItem.getTeam().getBalance();
		if (currentBalance < price) {
			if (Nomads.DEBUGMOVES)
				System.out.println("You do not have enough money!");
			return false;
		} else
			return true;
	}

	/**
	 * Find out how much money your team has
	 * 
	 * @return <code>int</code> - Team Balance
	 */
	public int getTeamBalance() {
		return currentTeam.getBalance();
	}

	/**
	 * Calculates and returns the distance of the drone from a provided point.
	 * Number is rounded up to nearest whole number >= actual distance.
	 * 
	 * @param p
	 *            Point to find distance to
	 * @return <code>int</code>
	 */
	public int distanceFromPoint(Point p) {
		return 1;
		// TODO - Implement distanceFromePoint
	}
}
