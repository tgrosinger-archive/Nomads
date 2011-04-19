package net.grosinger.nomads;

public class Nomads {

	public static void main(String[] args) {
		// Load the world
		World awesomeWorld = new World();

		// Initialize and save all the drones
		// TODO - Save what this returns to a variable
		LoadDrones.initializeDrones();

		// Generate the locations of the main buildings in the world

		// Load the drones in to the world

		// Start the game loop
	}

	/*
	 * Main game loop Continues to run until there is either a winner or the
	 * game is told to end. Returns the team that was the winner. Null if there
	 * was no winner.
	 */
	public DroneTeam running() {

		return null;
	}
}
