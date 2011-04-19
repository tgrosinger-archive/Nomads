package net.grosinger.nomads;

import java.util.ArrayList;

public class Nomads {

	public static World awesomeWorld;
	public static DroneListItem firstDrone;
	public static ArrayList<DroneTeam> allTeams;

	public static void main(String[] args) {
		// Load the world
		awesomeWorld = new World();

		// Initialize and save all the drones
		// This will update firstDrone and allTeams
		InitializeGame.initializeDrones();

		// Check to make sure firstDrone and allTeams have been set up properly
		if (firstDrone == null || allTeams == null) {
			// Do something awesome
			// Or catch the error
		}

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
