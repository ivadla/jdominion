package org.jdominion.event;

import org.jdominion.Player;

public abstract class Event {

	private String description;
	private Player affectedPlayer;

	public Player getAffectedPlayer() {
		return affectedPlayer;
	}

	public String getDescription() {
		return description;
	}

	public Event(String description, Player affectedPlayer) {
		this.description = description;
		this.affectedPlayer = affectedPlayer;
	}

}
