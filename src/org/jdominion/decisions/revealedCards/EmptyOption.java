package org.jdominion.decisions.revealedCards;

public class EmptyOption extends Option {

	private static EmptyOption instance = null;

	private EmptyOption() {
		super("");
	}

	public static EmptyOption getInstance() {
		if (instance == null) {
			instance = new EmptyOption();
		}

		return instance;
	}
}
