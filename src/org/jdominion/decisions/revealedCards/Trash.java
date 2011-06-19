package org.jdominion.decisions.revealedCards;

public class Trash extends Option {

	private static Trash instance = null;

	private Trash() {
	}

	public static Trash getInstance() {
		if (instance == null) {
			instance = new Trash();
		}

		return instance;
	}
}
