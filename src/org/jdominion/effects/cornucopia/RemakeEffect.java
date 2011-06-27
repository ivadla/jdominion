package org.jdominion.effects.cornucopia;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.UpgradeEffect;

public class RemakeEffect extends UpgradeEffect {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		super.execute(activePlayer, currentTurn, supply);
		super.execute(activePlayer, currentTurn, supply);
		return true;
	}

}
