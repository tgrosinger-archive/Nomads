package net.grosinger.nomads;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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

		// Obtain the class loader
		ClassLoader classLoader = InitializeGame.class.getClassLoader();

		// Obtain a list of the classes that exist in the directory
		String[] classesToLoad = generateList();

		// Loop through the list of filenames
		for (int i = 0; i < classesToLoad.length; i++) {
			String filename = classesToLoad[i];

			// Load the class
			if (Nomads.DEBUGSTATUS)
				System.out.println("Loading " + filename);
			String className = filename.substring(0, filename.length() - 4);

			File file = new File(System.getProperty("user.dir") + "/drones/" + filename);

			URLClassLoader clazzLoader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL() });
			// System.class.getClassLoader()

			JarFile jarFile = new JarFile(file);
			Enumeration<JarEntry> entries = jarFile.entries();

			while (entries.hasMoreElements()) {
				JarEntry element = entries.nextElement();
				if (element.getName().endsWith(".class")) {
					try {
						Class c = clazzLoader.loadClass(element.getName().replaceAll(".class", "").replaceAll("/", "."));
						System.out.println("Class loaded sucessfully");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			// Create an object from it

			// Create a DroneListItem with proper previous and next depending on
			// its position

			// Create a new DroneTeam which will automatically add it as the
			// first and last drone.

		}

		if (Nomads.DEBUGSTATUS)
			System.out.println("Drone loading complete");
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

		if (Nomads.DEBUGSTATUS)
			System.out.println("Building generation complete");

		if (Nomads.DEBUGSTATUS)
			System.out.println("Building placement complete");
	}
}
