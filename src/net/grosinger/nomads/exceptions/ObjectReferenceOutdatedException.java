package net.grosinger.nomads.exceptions;

public class ObjectReferenceOutdatedException extends Exception {

	public ObjectReferenceOutdatedException() {
		super("This object was created in a previous turn and is no longer valid");
	}
}
