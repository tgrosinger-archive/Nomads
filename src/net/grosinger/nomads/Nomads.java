package net.grosinger.nomads;

import java.io.IOException;
import java.util.ArrayList;

public class Nomads {

	public static World awesomeWorld;
	public static ArrayList<DroneTeam> allTeams = new ArrayList<DroneTeam>();
	public static boolean running = true;
	public static int turn;

	// Debugging Modes
	public static final boolean DEBUGSTATUS = true;
	public static final boolean DEBUGINITIALIZE = true;
	public static final boolean DEBUGMOVES = true;
	public static final boolean DEBUGDEATHS = true;
	public static final boolean DEBUGCREATIONS = true;
	public static final boolean DEBUGBUILDINGS = true;

	/**
	 * How frequently should a new map be generated? (In turns, 1 means every
	 * turn)
	 */
	public static final int MAPGENRATE = 2;

	/**
	 * How much should a house cost?
	 */
	public static final int HOUSEPRICE = 200;

	/**
	 * How much should a new drone cost?
	 */
	public static final int DRONEPRICE = 150;

	/**
	 * How many turns does it take to create a drone or house?
	 */
	public static final int CREATIONTIME = 200;

	/**
	 * How many randomly generated money piles should there be?
	 */
	public static final int MONEYPILES = 30;

	public static final int MAXOBJECTIVEBOUNTY = 30;
	public static final int MINOBJECTIVEBOUNTY = 10;
	
	public static final int BASE_VISIBLEDISTANCE = 15;
	public static final int BASE_LUMALOCATORDISTANCE = 10;
	public static final int BASE_OBJECTLOCATORDISTANCE = 10;
	public static final int BASE_RELIABILITY = 1;
	public static final int BASE_ATTACK = 1;
	public static final int BASE_DEFENSES = 1;
	public static final int BASE_SPEED = 1;
	public static final int BASE_CARGOSPACE = 3;
	public static final int BASE_THEFT = 1;

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

		turn = 0;
		int counter = 0;

		while (running) {
			counter++;
			turn++;

			long startTime = System.currentTimeMillis();

			for (DroneTeam currentTeam : allTeams) {
				currentTeam.getMoves();
			}

			long endTime = System.currentTimeMillis();

			if (DEBUGSTATUS)
				System.out.println("Moves took " + (endTime - startTime) + "milliseconds");

			// Create a new map
			if (counter == MAPGENRATE) {
				awesomeWorld.generateMap(turn);
				counter = 0;
			}

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

	/**
	 * Output the winning messages
	 * 
	 * @param winner
	 *            - The team that won
	 */
	public static void finishGame(DroneTeam winner) {
		if (winner == null)
			System.out.println("There was no winner, please play again");
		else
			System.out.println("The winner was " + winner.getName());
	}

	/**
	 * Find the last team alive, or the one with the most money
	 * 
	 * @return <code>DroneTeam</code>
	 */
	public static DroneTeam getWinner() {
		// When the last drone in the team is killed it will be removed from the
		// list

		// If there is only 1 drone left, it is the winner
		if (allTeams.size() == 1)
			return allTeams.get(0);
		else if (allTeams.isEmpty()) {
			// No winner, this probably won't happen
			return null;
		} else {
			// There were multiple teams left, check their balances
			DroneTeam winner = null;
			int highestBalance = 0;
			for (DroneTeam team : allTeams) {
				if (team.getBalance() > highestBalance)
					winner = team;
			}
			return winner;
		}
	}

	/**
	 * Matches a Drone to a DroneListItem
	 * 
	 * @param theDrone
	 *            <code>Drone</code> to be found
	 * @return <code>DroneListItem</code>
	 */
	public static DroneListItem droneToListItem(Drone theDrone) {
		for (DroneTeam team : allTeams) {
			DroneListItem current = team.getFirst();
			if (current.getCurrent().getUID().equals(theDrone.getUID()))
				return current;
		}
		return null;
	}

	/**
	 * Matches a UID to a Drone
	 * 
	 * @param UID
	 *            <code>String</code> UID that needs to be found
	 * @return <code>DroneListItem</code>
	 */
	public static DroneListItem UIDToListItem(String UID) {
		for (DroneTeam team : allTeams) {
			DroneListItem current = team.getFirst();
			if (current.getCurrent().getUID().equals(UID))
				return current;
		}
		return null;
	}
}
