package org.jdominion.event;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Util;

public class CardsDiscarded extends Event {

	private List<Card> discardedCards;

	public List<Card> getDiscardedCards() {
		return discardedCards;
	}

	public CardsDiscarded(Player discardingPlayer, List<Card> discardedCards) {
		super(discardingPlayer.getName() + " discards " + Util.convertCardListToString(discardedCards) + ".",
				discardingPlayer);
		this.discardedCards = discardedCards;
	}

}
