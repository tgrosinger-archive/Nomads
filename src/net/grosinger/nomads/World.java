package net.grosinger.nomads;

/**
 * Main class where information about the world is stored
 * 
 * Consists of a 2D array, representing each possible space in the game grid
 * (this might take a lot of space)
 */
public class World {

	/**
	 * The dimensions of x and y in the game world
	 */
	private final static int WORLDSIZE = 100;

	/**
	 * The 2D array of the entire world
	 */
	private GameObject theWorld[][];

	/**
	 * All drones will start at Y=20. Will then move East from X=40
	 */
	private int lastUsedX = 40;

	/**
	 * Class constructor
	 */
	public World() {
		if (Nomads.DEBUGSTATUS)
			System.out.println("Intializing the world...");

		theWorld = new GameObject[WORLDSIZE][WORLDSIZE];

		if (Nomads.DEBUGSTATUS)
			System.out.println("World initialization complete");
	}

	/**
	 * Moves the object at x, y by the specified amount
	 * 
	 * @param startingX
	 *            X index of starting location
	 * @param startingY
	 *            Y index of starting location
	 * @param amountN
	 *            Amount to move the object North (negative will move South)
	 * @param amountE
	 *            Amount to move the object East (negative will move West)
	 */
	public void moveObjectAt(int startingX, int startingY, int amountN, int amountE) {
		GameObject tempStorage = theWorld[startingX][startingY];
		theWorld[startingX][startingY] = null;

		int newX = startingX + amountE;
		int newY = startingY + amountN;

		theWorld[newX][newY] = tempStorage;
	}

	// Getters and Setters

	/**
	 * Returns the main world that all the GameObjects are on
	 * 
	 * @return theWorld
	 */
	public GameObject[][] getWorldGrid() {
		return theWorld;
	}

	/**
	 * Returns a GameObject located at given position
	 * 
	 * @param x
	 *            The X index to retrieve
	 * @param y
	 *            The Y index to retrieve
	 * @return GameObject at given location
	 */
	public GameObject getObjectAt(int x, int y) {
		return theWorld[x][y];
	}

	/**
	 * Retrieves the length of X and Y axis of the world (square)
	 * 
	 * @return A single int, representing the length of X and Y
	 */
	public int getWorldSize() {
		return WORLDSIZE;
	}

	/**
	 * Used to set a new GameObject at a given location. Not used for moving a
	 * GameObject
	 * 
	 * @param x
	 *            X index to place GameObject at
	 * @param y
	 *            Y index to place GameObject at
	 * @param newItem
	 *            GameObject to be placed
	 */
	public void setObjectAt(int x, int y, GameObject newItem) {
		theWorld[x][y] = newItem;
	}

	/**
	 * Places a GameObject at a random location in the World
	 * 
	 * @param newItem
	 *            GameObject to be placed
	 */
	public void setObjectRandom(GameObject newItem) {
		// Min + (int)(Math.random() * ((Max - Min) + 1))
		int randX = 0 + (int) (Math.random() * ((getWorldSize() - 0) + 1));
		int randY = 0 + (int) (Math.random() * ((getWorldSize() - 0) + 1));
		setObjectAt(randX, randY, newItem);
	}

	/**
	 * Places a drone in the world at it's starting point
	 * 
	 * @param newDrone
	 *            <code>DroneListItem</code> to be placed.
	 */
	public void placeNewDrone(DroneListItem newDrone) {
		lastUsedX++;
		newDrone.setX(lastUsedX);
		newDrone.setY(20);

		// The actual placement
		setObjectAt(lastUsedX, 20, newDrone.getCurrent());
	}

	public boolean inSafeZone(int x, int y) {
		/*
		 * Safe Zones - Measured in radius of a square TownHall - 3 : RepairShop
		 * - 2 : UpgradeShop - 2 : PoliceStation - 3 : Home - 1
		 */

		// Maximum distance away a building that provides a safehouse is 3
		for (int i = -3; i <= 3; i++) {
			for (int j = -3; j <= 3; j++) {
				//Prevent OutofBounds.  Indexes = - WORLDSIZE-1
				if (x + i >= WORLDSIZE-1 || x + i < 0 || y + j >= WORLDSIZE-1 || y + j < 0) {

				} else {
					GameObject objectHere = theWorld[x + i][y + j];
					if (objectHere != null) { // TODO - ignore 0, 0
						String name = objectHere.getName();
						if (name.equalsIgnoreCase("TownHall") || name.equalsIgnoreCase("PoliceStation")) {
							return true;
						} else if ((name.equalsIgnoreCase("RepairShop") || name.equalsIgnoreCase("UpgradeShop")) && i <= 2 && j <= 2) {
							return true;
						}
						// TODO - Include Team Houses in the safe zone test
					}
				}
			}
		}
		return false;
	}
}
