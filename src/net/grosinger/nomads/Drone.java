package net.grosinger.nomads;

//Interface for users to base their drones off.
public interface Drone {

	// Probably needs to be made more specific or something
	void move();

	// Returns how many turns this drone has been alive
	int getAge();

}
