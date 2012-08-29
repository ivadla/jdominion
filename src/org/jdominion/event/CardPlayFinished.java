package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardPlayFinished extends CardEvent {

	public CardPlayFinished(Player owner, Card playedCard, Turn currentTurn, Supply supply) {
		super("finished playing", owner, playedCard, currentTurn, supply);
	}

}
