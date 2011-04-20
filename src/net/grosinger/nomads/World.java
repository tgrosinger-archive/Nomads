package net.grosinger.nomads;

/*
 * Main class where information about the world is stored
 * 
 * Consists of a 2D array, representing each possible space in the game grid
 * (this might take a lot of space)
 */
public class World {

	// The dimensions of x and y in the game world
	private final static int WORLDSIZE = 100;

	// The 2D array of the entire world
	// TODO - This will never actually hold a GameObject, will always be a type
	// of drone or building. Make that work
	private static GameObject theWorld[][] = new GameObject[WORLDSIZE][WORLDSIZE];

	public World() {
		if (Nomads.DEBUGSTATUS)
			System.out.println("Intializing the world...");
		// TODO - Implement World Constructor

		if (Nomads.DEBUGSTATUS)
			System.out.println("World initialization complete");
	}

	// Moves the object at x,y to a new x,y
	public void moveObjectAt(int startingX, int startingY, int amountN, int amountE) {
		// TODO - Implement moveObjectAt
	}

	// Getters and Setters

	public GameObject[][] getWorldGrid() {
		return theWorld;
	}

	public GameObject getObjectAt(int x, int y) {
		return theWorld[x][y];
	}

	public int getWorldSize() {
		return WORLDSIZE;
	}

	// Why would we need a setter for theWorld?

	public void setObjectAt(int x, int y, GameObject newItem) {
		theWorld[x][y] = newItem;
	}
}
