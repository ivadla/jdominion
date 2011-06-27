package org.jdominion.simulation;

import org.jdominion.Player;
import org.jdominion.cards.common.Curse;

public class Cursecounter implements IExtraStatistics {

	@Override
	public int getValue(Player player) {
		return player.countCardsOfClass(Curse.class, null);
	}

	@Override
	public String getName() {
		return "Curses";
	}

}
