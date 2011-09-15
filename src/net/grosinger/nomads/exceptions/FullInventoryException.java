package net.grosinger.nomads.exceptions;

public class FullInventoryException extends Exception {

	public FullInventoryException() {
		super("The inventory is already full");
	}
}
