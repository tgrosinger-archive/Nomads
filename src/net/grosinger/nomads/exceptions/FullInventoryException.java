package net.grosinger.nomads.exceptions;

@SuppressWarnings("serial")
public class FullInventoryException extends Exception {

	public FullInventoryException() {
		super("The inventory is already full");
	}
}
