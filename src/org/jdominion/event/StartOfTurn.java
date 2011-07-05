package org.jdominion.event;

import org.jdominion.Turn;

public class StartOfTurn extends Event {

	public StartOfTurn(Turn startingTurn) {
		super("Turn " + startingTurn.getTurnNumber() + " of player " + startingTurn.getActivePlayer().getName()
				+ " starts.", startingTurn.getActivePlayer());
	}

}
