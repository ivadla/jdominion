package org.jdominion.decisions.revealedCards;

public class Gain extends Option {

	private static Gain instance = null;

	private Gain() {
	}

	public static Gain getInstance() {
		if (instance == null) {
			instance = new Gain();
		}

		return instance;
	}
}
