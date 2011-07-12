package org.jdominion.event;

import org.jdominion.CardList;
import org.jdominion.Player;

public class CardsDiscarded extends Event {

	private CardList discardedCards;

	public CardList getDiscardedCards() {
		return discardedCards;
	}

	public CardsDiscarded(Player discardingPlayer, CardList discardedCards) {
		super(discardingPlayer.getName() + " discards " + discardedCards.convertToString() + ".", discardingPlayer);
		this.discardedCards = discardedCards;
	}

}
