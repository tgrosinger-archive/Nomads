package net.grosinger.nomads;

/**
 * Just used to transport locations between two methods
 */
public class Point {
	private int x;
	private int y;

	public Point(int newX, int newY) {
		x = newX;
		y = newY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int newX) {
		x = newX;
	}

	public void setY(int newY) {
		y = newY;
	}
}
