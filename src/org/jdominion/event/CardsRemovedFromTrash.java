package org.jdominion.event;

import org.jdominion.CardList;

public class CardsRemovedFromTrash extends Event {
	private CardList removedCards;

	public CardList getRemovedCards() {
		return removedCards;
	}

	public CardsRemovedFromTrash(CardList removedCards) {
		super(removedCards.convertToString() + " removed from trash.", null);
		this.removedCards = removedCards;
	}
}
