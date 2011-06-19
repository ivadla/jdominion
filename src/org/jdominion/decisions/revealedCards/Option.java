package org.jdominion.decisions.revealedCards;

public class Option {

	private String name;

	public Option(String name) {
		this.name = name;
	}

	public Option() {
		this.name = getClass().getSimpleName();
	}

	public String getName() {
		return name;
	}
}
