package net.grosinger.nomads;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.grosinger.nomads.buildings.Building;
import net.grosinger.nomads.buildings.Building.Structure;
import net.grosinger.nomads.drones.Drone;
import net.grosinger.nomads.drones.DroneTeam;

/**
 * Various methods used when first setting up the game and loading everything
 * into the world.
 */
public class InitializeGame {

	/**
	 * Search through ___ directory for any drone classes Load them, find the
	 * name, and return a linked-list with all the drones
	 * 
	 * Must set Nomads.firstDrone and Nomads.allTeams
	 * 
	 * Each drone type will be a new DroneTeam
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void initializeDrones() throws ClassNotFoundException, IOException {
		if (Nomads.DEBUGSTATUS)
			System.out.println("Loading the drones into world...");
		/*
		 * Steps
		 */

		// Obtain a list of the classes that exist in the directory
		String[] classesToLoad = generateList();
		if (classesToLoad.length == 0) {
			System.out.println("No Drones to load");
			Nomads.running = false;
		}

		// Loop through the list of filenames
		for (int i = 0; i < classesToLoad.length; i++) {
			String filename = classesToLoad[i];
			String className = filename.substring(0, filename.length() - 4);

			// Load the class
			if (Nomads.DEBUGSTATUS)
				System.out.println("Loading " + filename);

			File file = new File(System.getProperty("user.dir") + "/drones/" + filename);

			URLClassLoader clazzLoader = URLClassLoader.newInstance(new URL[] { file.toURI()
					.toURL() });
			// System.class.getClassLoader()

			JarFile jarFile = new JarFile(file);
			Enumeration<JarEntry> entries = jarFile.entries();

			while (entries.hasMoreElements()) {
				JarEntry element = entries.nextElement();
				if (element.getName().endsWith(className + ".class")) {
					try {
						@SuppressWarnings("rawtypes")
						Class c = clazzLoader.loadClass(element.getName().replaceAll(".class", "")
								.replaceAll("/", "."));

						// Create new GameObject
						GameObject newGameObject = (GameObject) c.newInstance();
						newGameObject.setName(className);
						Drone newDrone = (Drone) newGameObject;
						newDrone.setUID(generateUID());

						createNewDrone(newGameObject);

						System.out.println("Class loaded sucessfully");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (Nomads.DEBUGSTATUS)
			System.out.println("Drone loading complete");
	}

	private static void createNewDrone(GameObject newDrone) {
		// Create a DroneListItem and DroneTeam
		DroneTeam newTeam = new DroneTeam(newDrone);

		// Place in the array
		Nomads.allTeams.add(newTeam);
	}

	/**
	 * Creates a list of drones that are in the "drones" directory If directory
	 * does not exist it will create it
	 * 
	 * @return <code>String[]</code> of all the file names
	 */
	private static String[] generateList() {
		File dir = new File("drones");

		// if the directory does not exist, create it
		if (!dir.exists()) {
			if (Nomads.DEBUGINITIALIZE)
				System.out.println("creating directory: drones");
			boolean result = dir.mkdir();
			if (result && Nomads.DEBUGINITIALIZE) {
				System.out.println("DIR created");
			}
		}

		String[] children = dir.list();
		if (children == null) {
			// Either directory does not exist or is not a directory
			return null;
		} else {
			return children;
		}
	}

	/**
	 * Creates a unique identifier for each drone that is loaded
	 * 
	 * @return <code>String</code>- UID
	 */
	public static String generateUID() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	/**
	 * Generates and places required buildings in the world
	 * 
	 * @param awesomeWorld
	 *            Main world the game uses
	 */
	public static void initializeBuildngs(World awesomeWorld) {
		// Buildings to create: Town Hall, Repair Shop, Upgrade Shop, Police
		// Station
		if (Nomads.DEBUGSTATUS)
			System.out.println("Generating and placing required buildings...");

		Building townHall = new Building(Structure.TOWNHALL, 30, 40, null);
		Building upgradeShop = new Building(Structure.UPGRADESHOP, 30, 60, null);
		Building policeStation = new Building(Structure.POLICESTATION, 50, 40, null);
		Building RepairShop = new Building(Structure.REPAIRSHOP, 50, 60, null);

		if (Nomads.DEBUGSTATUS)
			System.out.println("Building generation complete");

		awesomeWorld.placeNewBuilding(townHall);
		awesomeWorld.placeNewBuilding(upgradeShop);
		awesomeWorld.placeNewBuilding(policeStation);
		awesomeWorld.placeNewBuilding(RepairShop);

		if (Nomads.DEBUGSTATUS)
			System.out.println("Building placement complete");
	}

	/**
	 * Generate the money piles and place them on the map
	 */
	public static void initializeMoneyPiles(World awesomeWorld) {
		for (int i = 0; i < Nomads.MONEYPILES; i++) {
			MoneyPile newPile = new MoneyPile();
			awesomeWorld.setObjectRandom(newPile);
		}
	}
}
