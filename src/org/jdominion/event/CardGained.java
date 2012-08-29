package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardGained extends CardEvent {

	public CardGained(Player affectedPlayer, Card gainedCard, Turn currentTurn, Supply supply) {
		super("gained", affectedPlayer, gainedCard, currentTurn, supply);
	}

}
