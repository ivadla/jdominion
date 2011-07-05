package org.jdominion.event;

import org.jdominion.Player;

public abstract class CancelableEvent extends Event {

	private boolean canceled = false;

	public CancelableEvent(String description, Player affectedPlayer) {
		super(description, affectedPlayer);
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean cancel) {
		this.canceled = cancel;
	}

}
