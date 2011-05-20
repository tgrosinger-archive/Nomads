package net.grosinger.nomads;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private GameObject[][] theWorld;

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
	 * Moves the object at x, y by the specified amount. If there is already an
	 * object there it will overwrite that object.
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
	public GameObject[][] getWorld() {
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

	/**
	 * Places a building in the world
	 * 
	 * @param newBuilding
	 *            The building to be placed
	 */
	public void placeNewBuilding(Building newBuilding) {
		int x = newBuilding.getX();
		int y = newBuilding.getY();

		theWorld[x][y] = newBuilding;
	}

	/**
	 * Returns if the Drone at given coordinates is in a safe zone
	 * 
	 * @param x
	 *            - Specified X value
	 * @param y
	 *            - Specified Y value
	 * @return <code>boolean</code>
	 */
	public boolean inSafeZone(int x, int y, DroneListItem listItem) {
		/*
		 * Safe Zones - Measured in radius of a square TownHall - 3 : RepairShop
		 * - 2 : UpgradeShop - 2 : PoliceStation - 3 : Home - 1
		 */

		// Maximum distance away a building that provides a safehouse is 3
		for (int i = -3; i <= 3; i++) {
			for (int j = -3; j <= 3; j++) {
				// Prevent OutofBounds. Indexes = - WORLDSIZE-1
				if (x + i >= WORLDSIZE - 1 || x + i < 0 || y + j >= WORLDSIZE - 1 || y + j < 0) {

				} else if (i != 0 && j != 0) {
					GameObject objectHere = theWorld[x + i][y + j];
					if (objectHere != null) {
						String name = objectHere.getName();
						if (name.equalsIgnoreCase("TownHall") || name.equalsIgnoreCase("PoliceStation")) {
							return true;
						} else if ((name.equalsIgnoreCase("RepairShop") || name.equalsIgnoreCase("UpgradeShop")) && i <= 2 && j <= 2) {
							return true;
						}
					}
				}
			}
		}
		// Check to see if they are within the safe-zone of any of the team
		// houses. Houses provide 1 radius around the building (total 3x3)
		ArrayList<House> teamHouses = listItem.getTeam().getTeamHouses();
		for (House currentHouse : teamHouses) {
			int houseX = currentHouse.getX();
			int houseY = currentHouse.getY();
			if (houseX + 1 > x && houseX - 1 < x && houseY + 1 > y && houseY - 1 < y) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Searches for any buildings within range spaces of x,y
	 * 
	 * @param x
	 *            - X Index
	 * @param y
	 *            - Y Index
	 * @param range
	 *            - range to search
	 * @return <code>ArrayList(building)</code>
	 */
	public ArrayList<Building> buildingsInRange(int x, int y, int range) {
		// TODO - Implement buildingsInRange
		return null;
	}

	/**
	 * Generates a new MoneyPile at random location
	 */
	public void generateMoneyPile() {
		MoneyPile newPile = new MoneyPile();
		setObjectRandom(newPile);
	}

	/**
	 * Outputs an HTML file showing the world
	 */
	public void generateMap(int turn) {
		ArrayList<Color> allColors = new ArrayList<Color>();
		addColors(allColors);
		Map<String, Color> colorMap = new HashMap<String, Color>();
		int counter = 0;

		// Create the color map
		for (DroneTeam team : Nomads.allTeams) {
			String name = team.getName();
			Color color = null;
			if (counter <= 8)
				color = allColors.get(counter);
			colorMap.put(name, color);
			counter++;
		}

		new File("maps").mkdir();
		BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();

		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, 1000, 1000);

		// draw grid lines
		g2d.setColor(Color.black);
		for (int i = 0; i <= 1000; i += 100) {
			g2d.drawLine(i, 0, i, 1000);
			g2d.drawLine(0, i, 1000, i);
		}
		g2d.drawLine(999, 0, 999, 1000);
		g2d.drawLine(0, 999, 1000, 999);

		for (int i = 0; i < WORLDSIZE; i++) {
			for (int j = 0; j < WORLDSIZE; j++) {
				GameObject objectHere = theWorld[j][i];
				if (objectHere == null) {
					// Do nothing
				} else if (objectHere instanceof Drone) {
					String name = objectHere.getName();
					Color color = colorMap.get(name);
					g2d.setColor(color);
					g2d.fillOval(j * 10, i * 10, 10, 10);
				} else if (objectHere instanceof Building) {
					// TODO - Add color-coding to buildings on map
					// World owned buildings should be black - use colorMap to
					// get color for each team
					g2d.setColor(Color.black);
					g2d.fillRect(j * 10, i * 10, 10, 10);
				} else if (objectHere instanceof MoneyPile) {
					// TODO - Implement mapping of MoneyPiles
					// Should be black since they are world owned but a
					// different shape than anything else
				} else if (objectHere instanceof Objective) {
					// TODO - Implement mapping of Objective
					// Should be black since they are world owned but a
					// different shape than anything else
				}
			}
		}
		writeImage(image, "maps/Map-Turn" + turn);
		// g2d.dispose();
	}

	private void addColors(ArrayList<Color> allColors) {
		allColors.add(Color.blue);
		allColors.add(Color.orange);
		allColors.add(Color.red);
		allColors.add(Color.green);
		allColors.add(Color.darkGray);
		allColors.add(Color.yellow);
		allColors.add(Color.cyan);
		allColors.add(Color.magenta);
	}

	private void writeImage(BufferedImage image, String fileName) {
		try {
			String ext = "png"; // bmp, gif, png, jpg okay
			File file = new File(fileName + "." + ext);
			javax.imageio.ImageIO.write(image, ext, file);
		} catch (IOException e) {
			System.out.println("write error: " + e.getMessage());
		}
	}
}
