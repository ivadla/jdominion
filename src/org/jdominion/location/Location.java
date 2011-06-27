package org.jdominion.location;

import org.jdominion.Card;
import org.jdominion.Player;

public abstract class Location {

	private String name;

	public String getName() {
		return name;
	}

	public Location(String name) {
		this.name = name;
	}

	public abstract void putCard(Player cardOwner, Card card);

}
