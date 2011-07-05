package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;

public class CardPlayed extends Event {

	private Card playedCard;

	public Card getPlayedCard() {
		return playedCard;
	}

	public CardPlayed(Player owner, Card playedCard) {
		super(owner.getName() + " plays " + playedCard.getName(), owner);
		this.playedCard = playedCard;
	}
}
