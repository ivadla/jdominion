package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;

public class CardPlayed extends Event {
	
	private Player owner;
	private Card playedCard;

	public Player getOwner() {
		return owner;
	}

	public Card getPlayedCard() {
		return playedCard;
	}

	public CardPlayed(Player owner, Card playedCard) {
		super(owner.getName() + " plays " + playedCard.getName());
		this.owner = owner;
		this.playedCard = playedCard;
	}
}
