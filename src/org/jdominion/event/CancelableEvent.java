package org.jdominion.event;

public abstract class CancelableEvent extends Event {

	private boolean canceled = false;

	public CancelableEvent(String description) {
		super(description);
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean cancel) {
		this.canceled = cancel;
	}

}
