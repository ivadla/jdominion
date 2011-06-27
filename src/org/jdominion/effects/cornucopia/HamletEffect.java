package org.jdominion.effects.cornucopia;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardFromHandToDiscardForExtraAction;
import org.jdominion.decisions.ChooseCardFromHandToDiscardForExtraBuy;
import org.jdominion.effects.CardEffectAction;

public class HamletEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		ChooseCardFromHandToDiscardForExtraAction actionDecision = new ChooseCardFromHandToDiscardForExtraAction(
				activePlayer.getHand());
		activePlayer.decide(actionDecision, this, activePlayer.getHand(), currentTurn, supply);
		if (!actionDecision.isCanceled()) {
			activePlayer.discardCardsFromHand(actionDecision.getAnswer());
			currentTurn.addActions(1);
		}

		ChooseCardFromHandToDiscardForExtraBuy buyDecision = new ChooseCardFromHandToDiscardForExtraBuy(activePlayer
				.getHand());
		activePlayer.decide(buyDecision, this, activePlayer.getHand(), currentTurn, supply);
		if (!buyDecision.isCanceled()) {
			activePlayer.discardCardsFromHand(buyDecision.getAnswer());
			currentTurn.addBuys(1);
		}

		return true;
	}

	@Override
	public int getAddedActions() {
		return 1;
	}

}
