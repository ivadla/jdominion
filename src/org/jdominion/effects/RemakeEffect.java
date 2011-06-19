package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class RemakeEffect extends UpgradeEffect {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		super.execute(activePlayer, currentTurn, supply);
		super.execute(activePlayer, currentTurn, supply);
		return true;
	}

}
