package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;

public class CardBought extends Event {

	private Card boughtCard;

	public Card getBoughtCard() {
		return boughtCard;
	}

	public CardBought(Player buyer, Card boughtCard) {
		super(buyer.getName() + " buys " + boughtCard.getName() + ".", buyer);
		this.boughtCard = boughtCard;
	}

}
