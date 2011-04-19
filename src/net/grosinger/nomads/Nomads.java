package net.grosinger.nomads;

import java.util.ArrayList;

public class Nomads {

	public static World awesomeWorld;
	public static DroneListItem firstDrone;
	public static ArrayList<DroneTeam> allTeams;

	public static void main(String[] args) {
		System.out.println("Game initialization beginning...");

		// Load the world
		awesomeWorld = new World();

		// Initialize and save all the drones
		// This will update firstDrone and allTeams
		InitializeGame.initializeDrones();

		// Generate and place all required buildings into world
		InitializeGame.initializeBuildngs(awesomeWorld);

		// Check to make sure firstDrone and allTeams have been set up properly
		if (firstDrone == null || allTeams == null) {
			// Do something awesome
			// Or catch the error
		}

		// Generate the locations of the main buildings in the world

		// Load the drones in to the world

		// Start the game loop
		System.out.println("Game initialization finished, going to game loop");
		DroneTeam winner = running();

		System.out.println("Resolving a winner...");
		finishGame(winner);
	}

	/*
	 * Main game loop Continues to run until there is either a winner or the
	 * game is told to end. Returns the team that was the winner. Null if there
	 * was no winner.
	 */
	public static DroneTeam running() {
		System.out.println("Game loop starting...");

		System.out.println("Game loop finished");
		return null;
	}

	public static void finishGame(DroneTeam winner) {
		if (winner == null)
			System.out.println("There was no winner, please play again");
		else
			System.out.println("The winner was " + winner.getName());
	}
}
