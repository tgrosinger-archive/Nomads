package net.grosinger.nomads;

import java.io.IOException;
import java.util.ArrayList;

public class Nomads {

	public static World awesomeWorld;
	public static ArrayList<DroneTeam> allTeams = new ArrayList<DroneTeam>();
	public static boolean running = true;

	// Debugging Modes
	public static final boolean DEBUGSTATUS = true;
	public static final boolean DEBUGINITIALIZE = true;
	public static final boolean DEBUGMOVES = true;
	public static final boolean DEBUGDEATHS = true;
	public static final boolean DEBUGCREATIONS = true;
	public static final boolean DEBUGBUILDINGS = true;

	public static void main(String[] args) {
		if (DEBUGSTATUS)
			System.out.println("Game initialization beginning...");

		// Load the world
		awesomeWorld = new World();

		// Initialize and save all the drones
		// This will update firstDrone and allTeams
		try {
			InitializeGame.initializeDrones();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Generate and place all required buildings into world
		if (DEBUGSTATUS)
			InitializeGame.initializeBuildngs(awesomeWorld);

		// Check to make sure allTeams has been set up properly
		if (allTeams == null) {
			// Do something awesome
			// Or catch the error
		}

		// Start the game loop
		if (DEBUGSTATUS)
			System.out.println("Game initialization finished, going to game loop");
		DroneTeam winner = running();

		if (DEBUGSTATUS)
			System.out.println("Resolving a winner...");
		finishGame(winner);
	}

	/*
	 * Main game loop Continues to run until there is either a winner or the
	 * game is told to end. Returns the team that was the winner. Null if there
	 * was no winner.
	 */
	public static DroneTeam running() {
		if (DEBUGSTATUS)
			System.out.println("Game loop starting...");

		while (running) {

			long startTime = System.currentTimeMillis();

			for (DroneTeam currentTeam : allTeams) {
				currentTeam.getMoves();
			}

			long endTime = System.currentTimeMillis();

			if (DEBUGSTATUS)
				System.out.println("Moves took " + (endTime - startTime) + "milliseconds");

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// For testing purposes...
			// running = false;
		}

		if (DEBUGSTATUS)
			System.out.println("Game loop finished");
		return getWinner();
	}

	public static void finishGame(DroneTeam winner) {
		if (winner == null)
			System.out.println("There was no winner, please play again");
		else
			System.out.println("The winner was " + winner.getName());
	}

	public static DroneTeam getWinner() {
		// TODO - Implement getWinner
		return null;
	}

	public static DroneListItem droneToListItem(Drone theDrone) {
		for (DroneTeam team : allTeams) {
			DroneListItem current = team.getFirst();
			if (current.getCurrent().getUID() == theDrone.getUID())
				return current;
		}
		return null;
	}
}
