package net.grosinger.nomads.exceptions;

public class ObjectReferenceOutdated extends Exception {

	public ObjectReferenceOutdated() {
		super("This object was created in a previous turn and is no longer valid");
	}
}
