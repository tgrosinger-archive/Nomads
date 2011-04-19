package net.grosinger.nomads;

/*
 * Various methods used when first setting up the game and loading everything into the world.
 */
public class InitializeGame {

	/*
	 * Search through ___ directory for any drone classes Load them, find the
	 * name, and return a linkedlist with all the drones
	 * 
	 * Must set Nomads.firstDrone and Nomads.allTeams
	 * 
	 * Each drone type will be a new DroneTeam
	 */
	public static void initializeDrones() {
		System.out.println("Loading the drones into world...");
		/*
		 * Steps
		 */

		// Discover a new Drone

		// Create a DroneListItem with proper previous and next depending on its
		// position

		// Create a new DroneTeam which will automatically add it as the first
		// and last drone.
		System.out.println("Drone loading complete");
	}

	/*
	 * Buildings to create: Town Hall, Repair Shop, Upgrade Shop, Police Station
	 */
	public static void initializeBuildngs(World awesomeWorld) {
		System.out.println("Generating and placing required buildings...");

		System.out.println("Building generation complete");

		System.out.println("Building placement complete");
	}
}
