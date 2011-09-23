package net.grosinger.nomads.exceptions;

@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {

	public InsufficientFundsException() {
		super("The team you are a part of does not have the required funds for this action.");
	}
}
