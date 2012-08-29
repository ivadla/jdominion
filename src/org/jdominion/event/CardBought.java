package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardBought extends CardEvent {


	public CardBought(Player affectedPlayer, Card boughtCard, Turn currentTurn, Supply supply) {
		super("bought", affectedPlayer, boughtCard, currentTurn, supply);
	}

	public Card getBoughtCard() {
		return super.getCard();
	}


}
