package org.jdominion.decisions.revealedCards;

public class Discard extends Option {

	private static Discard instance = null;

	private Discard() {
	}

	public static Discard getInstance() {
		if (instance == null) {
			instance = new Discard();
		}

		return instance;
	}
}
