package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardGainedFromTrash extends CardEvent {

	public Card getRemovedCard() {
		return getCard();
	}

	public CardGainedFromTrash(Card cardToGainFromTrash, Player gainingPlayer, Turn currentTurn, Supply supply) {
		super(gainingPlayer, cardToGainFromTrash, currentTurn, supply, cardToGainFromTrash.getName() + " gained from trash.");
	}
}
