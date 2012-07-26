package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardFromHandToPutOnDeck;

public class PutCardFromHandOnTopOfDeck extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (activePlayer.getHand().size() == 0) {
			return false;
		}
		ChooseCardFromHandToPutOnDeck decision = new ChooseCardFromHandToPutOnDeck(activePlayer.getHand());
		activePlayer.decide(decision, this);
		assert decision.getAnswer().size() == 1;
		activePlayer.placeOnDeck(decision.getAnswer().getFirst());
		return true;
	}

}
