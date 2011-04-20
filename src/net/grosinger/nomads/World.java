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
	private static GameObject theWorld[][] = new GameObject[WORLDSIZE][WORLDSIZE];

	/**
	 * Class constructor
	 */
	public World() {
		if (Nomads.DEBUGSTATUS)
			System.out.println("Intializing the world...");
		// TODO - Implement World Constructor

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

	// Why would we need a setter for theWorld?

	/**
	 * Used to set a new GameObject at a given location Not used for moving a
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
}
