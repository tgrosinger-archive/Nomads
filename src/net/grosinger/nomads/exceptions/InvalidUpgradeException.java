package net.grosinger.nomads.exceptions;

public class InvalidUpgradeException extends Exception {
	
	public InvalidUpgradeException(String reason){
		super(reason);
	}
}
