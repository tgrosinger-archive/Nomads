package net.grosinger.nomads.exceptions;

@SuppressWarnings("serial")
public class InvalidUpgradeException extends Exception {
	
	public InvalidUpgradeException(String reason){
		super(reason);
	}
}
