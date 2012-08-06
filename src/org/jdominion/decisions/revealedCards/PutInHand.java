package org.jdominion.decisions.revealedCards;

public class PutInHand extends Option {
	private static PutInHand instance = null;

	private PutInHand() {
	}

	public static PutInHand getInstance() {
		if (instance == null) {
			instance = new PutInHand();
		}

		return instance;
	}
}
