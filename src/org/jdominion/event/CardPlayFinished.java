package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;

public class CardPlayFinished extends Event {

	private Card playedCard;

	public Card getPlayedCard() {
		return playedCard;
	}

	public CardPlayFinished(Player owner, Card playedCard) {
		super(owner.getName() + " finished playing " + playedCard.getName(), owner);
		this.playedCard = playedCard;
	}

}
