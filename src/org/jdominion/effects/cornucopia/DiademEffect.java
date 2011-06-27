package org.jdominion.effects.cornucopia;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectTreasure;

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
