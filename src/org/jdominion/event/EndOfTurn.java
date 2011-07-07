package org.jdominion.event;

import org.jdominion.Turn;

public class EndOfTurn extends Event {

	public EndOfTurn(Turn endingTurn) {
		super("Turn " + endingTurn.getTurnNumber() + " of player " + endingTurn.getActivePlayer().getName() + " has ended.", endingTurn.getActivePlayer());
	}

}
