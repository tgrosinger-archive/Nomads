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
	private static GameObject theWorld[][] = new GameObject[WORLDSIZE][WORLDSIZE];

	public World() {
		if (Nomads.DEBUGSTATUS)
			System.out.println("Intializing the world...");
		// TODO - Implement World Constructor

		if (Nomads.DEBUGSTATUS)
			System.out.println("World initialization complete");
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
