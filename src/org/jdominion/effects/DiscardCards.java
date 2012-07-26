package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardFromHandToDiscard;

public class DiscardCards extends CardEffectAction {

	private int numberOfCardsToDiscard;

	public DiscardCards(int numberOfCardsToDiscard) {
		this.numberOfCardsToDiscard = numberOfCardsToDiscard;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {

		for (int i = 0; i < numberOfCardsToDiscard; i++) {
			if (activePlayer.getHandSize() == 0) {
				return false;
			}
			ChooseCardFromHandToDiscard decision = new ChooseCardFromHandToDiscard(false, activePlayer.getHand());
			activePlayer.decide(decision, this);
			activePlayer.discardCardsFromHand(decision.getAnswer());
		}
		return true;
	}

}
