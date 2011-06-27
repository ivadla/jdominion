package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class DiademEffect extends CardEffectTreasure {

	public DiademEffect() {
		super(0);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		currentTurn.addExtraMoney(currentTurn.getAvailableActions());
		return true;
	}

}
