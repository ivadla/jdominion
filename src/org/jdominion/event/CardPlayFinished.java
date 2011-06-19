package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;

public class CardPlayFinished extends Event {

	private Player owner;
	private Card playedCard;

	public Player getOwner() {
		return owner;
	}

	public Card getPlayedCard() {
		return playedCard;
	}

	public CardPlayFinished(Player owner, Card playedCard) {
		super(owner.getName() + " finished playing " + playedCard.getName());
		this.owner = owner;
		this.playedCard = playedCard;
	}

}
