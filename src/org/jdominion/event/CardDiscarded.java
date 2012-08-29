package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardDiscarded extends CardEvent {

	public CardDiscarded(Player discardingPlayer, Card discardedCard, Turn currentTurn, Supply supply) {
		super("discards", discardingPlayer, discardedCard, currentTurn, supply);
	}

}
