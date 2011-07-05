package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;

public class CardGained extends Event {

	private Card gainedCard;

	public Card getGainedCard() {
		return gainedCard;
	}

	public CardGained(Player gainingPlayer, Card gainedCard) {
		super(gainingPlayer.getName() + " gains " + gainedCard.getName() + ".", gainingPlayer);
		this.gainedCard = gainedCard;
	}

}
