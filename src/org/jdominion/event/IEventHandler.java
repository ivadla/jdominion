package org.jdominion.event;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public interface IEventHandler {

	public void handleEvent(Event event, Player activePlayer, Turn currentTurn, Supply supply);
}
