package org.jdominion.effects.intrigue;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectAction;

public class ConspiratorEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (currentTurn.getActionsPlayed() >= 3) {
			activePlayer.drawCardsIntoHand(1);
			currentTurn.addActions(1);
		}
		return true;
	}

}
