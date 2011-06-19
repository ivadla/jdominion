package org.jdominion.event;

public abstract class Event {

	private String description;

	public Event(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
