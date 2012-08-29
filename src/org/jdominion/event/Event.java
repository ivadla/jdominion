package org.jdominion.event;

import org.jdominion.Player;

public abstract class Event {

	private String description;
	private Player affectedPlayer;
	private boolean isCancelable = false;
	private boolean canceled = false;

	public Player getAffectedPlayer() {
		return affectedPlayer;
	}

	public String getDescription() {
		return description;
	}

	public boolean isCancelable() {
		return isCancelable;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean cancel) {
		assert isCancelable || cancel == false : "This event is not cancelable";
		this.canceled = cancel;
	}

	public Event(String description, Player affectedPlayer) {
		this.description = description;
		this.affectedPlayer = affectedPlayer;
	}

	public Event(String description, Player affectedPlayer, boolean isCancelable) {
		this(description, affectedPlayer);
		this.isCancelable = isCancelable;
	}

}
