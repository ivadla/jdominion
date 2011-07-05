package org.jdominion.event;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Util;

public class CardsRevealed extends Event {

	private List<Card> revealedCards;

	public List<Card> getRevealedCards() {
		return revealedCards;
	}

	public CardsRevealed(Player revealingPlayer, List<Card> revealedCards) {
		super(revealingPlayer.getName() + " reveals " + Util.convertCardListToString(revealedCards) + ".",
				revealingPlayer);
		this.revealedCards = revealedCards;
	}
}
