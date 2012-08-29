package org.jdominion.effects.darkAges;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.darkAges.Cultist;
import org.jdominion.decisions.darkAges.PlayAnotherCultistDecision;
import org.jdominion.effects.CardEffectAction;

public class PlayAnotherCultistEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (activePlayer.getHand().contains(Cultist.class)) {
			PlayAnotherCultistDecision decision = new PlayAnotherCultistDecision();
			activePlayer.decide(decision, this);
			if (decision.getAnswer()) {
				currentTurn.playCard(activePlayer, supply, activePlayer.getHand().getCardByClass(Cultist.class));
				return true;
			}
		}
		return false;
	}

}
