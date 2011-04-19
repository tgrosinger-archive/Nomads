package net.grosinger.nomads;

/*
 * Everything that is found on the world will be a type of game object
 */
public class GameObject {
	private int x;
	private int y;
	protected String name;

	// Getters and Setters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public void setX(int newX) {
		x = newX;
	}

	public void setY(int newY) {
		y = newY;
	}

	public void setName(String newName) {
		name = newName;
	}
}
