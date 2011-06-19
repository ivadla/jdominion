package org.jdominion.decisions.revealedCards;

public class PutOnDeck extends Option {

	private static PutOnDeck instance = null;

	private PutOnDeck() {
	}

	public static PutOnDeck getInstance() {
		if (instance == null) {
			instance = new PutOnDeck();
		}

		return instance;
	}
}
