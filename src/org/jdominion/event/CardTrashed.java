package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardTrashed extends CardEvent {

	public CardTrashed(Player cardsOwner, Card trashedCard, Turn currentTurn, Supply supply) {
		super("trashes ", cardsOwner, trashedCard, currentTurn, supply);
	}

}
