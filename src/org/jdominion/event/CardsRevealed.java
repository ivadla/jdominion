package org.jdominion.event;

import org.jdominion.CardList;
import org.jdominion.Player;

//TODO: make this a CardEvent
public class CardsRevealed extends Event {

	private CardList revealedCards;

	public CardList getRevealedCards() {
		return revealedCards;
	}

	public CardsRevealed(Player revealingPlayer, CardList revealedCards) {
		super(revealingPlayer.getName() + " reveals " + revealedCards.convertToString() + ".", revealingPlayer);
		this.revealedCards = revealedCards;
	}
}
