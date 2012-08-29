package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardPlayed extends CardEvent {

	public CardPlayed(Player owner, Card playedCard, Turn currentTurn, Supply supply) {
		super("plays", owner, playedCard, currentTurn, supply);
	}
}
