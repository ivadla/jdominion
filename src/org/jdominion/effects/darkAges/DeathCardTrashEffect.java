package org.jdominion.effects.darkAges;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.darkAges.ChooseActionCardFromHandToTrash;
import org.jdominion.effects.CardEffectAction;

public class DeathCardTrashEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (activePlayer.hasActionCardInHand()) {
			ChooseActionCardFromHandToTrash decision = new ChooseActionCardFromHandToTrash(activePlayer.getHand(), true);
			activePlayer.decide(decision, this);
			if (!decision.isCanceled()) {
				activePlayer.trashCard(decision.getAnswer().getFirst(), currentTurn.getGame());
				return true;
			}
		}

		// no action card was trashed
		activePlayer.trashCard(getCard(), currentTurn.getGame());
		return true;
	}

}
