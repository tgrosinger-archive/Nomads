package net.grosinger.nomads;

/*
 * All drones will extend this class to give them the basic set of tools that they need.
 * Don't forget to set a name
 */
public class Drone extends GameObject {
	private int age;

	/*
	 * All custom drones should call the super constructor at the end of their
	 * constructor
	 */
	public Drone() {
		if (name == null) {
			System.out.println("Drone initialization failure, unnammed drone");
			// TODO - implement method of ending the game prematurely
		} else if (Nomads.DEBUGSTATUS)
			System.out.println("Drone initialized");
	}

	// Probably needs to be made more specific or something
	public void move() {

	}

	// Getters and Setters

	// Returns how many turns this drone has been alive
	public int getAge() {
		return age;
	}

	// Probably will not be needed.
	// TODO - find a way to prevent the drone from accessing this
	public void setAge(int newAge) {
		age = newAge;
	}

	// Increases the age by 1 turn
	public void incrementAge() {
		age++;
	}
}
