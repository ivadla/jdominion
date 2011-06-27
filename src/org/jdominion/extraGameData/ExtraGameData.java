package org.jdominion.extraGameData;

/**
 * The subclasses of ExtraGameData are used by cards to store data in the Game
 * (e.g. tokens on trade-route mat) or Player (e.g. VP Chips)
 * 
 */
public abstract class ExtraGameData<E> {

	private String name;
	private boolean isVisibleByAllPlayers;

	public String getName() {
		return name;
	}

	public boolean isVisibleByAllPlayers() {
		return isVisibleByAllPlayers;
	}

	protected ExtraGameData(String name, boolean isVisibleByAllPlayers) {
		this.name = name;
		this.isVisibleByAllPlayers = isVisibleByAllPlayers;
	}

	public abstract void set(E data);

	public abstract E get();

	public abstract void add(E data);

	public abstract String getContentsAsString();
}
