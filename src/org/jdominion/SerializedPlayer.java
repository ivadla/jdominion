package org.jdominion;

public class SerializedPlayer extends Player {

	private static final long serialVersionUID = 1L;
	private int handSize;
	private int deckSize;
	private int discardPileSize;

	public SerializedPlayer(String name, int handSize, int deckSize, int discardPileSize) {
		super(name);
		this.handSize = handSize;
		this.deckSize = deckSize;
		this.discardPileSize = discardPileSize;
	}

	@Override
	public int getDeckSize() {
		return deckSize;
	}

	@Override
	public int getDiscardPileSize() {
		return discardPileSize;
	}

	@Override
	public int getHandSize() {
		return handSize;
	}

}
