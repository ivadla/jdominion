package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.prosperity.DiscardCardForCoinDecision;

public class DiscardCardsForCoinsEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		int numberOfDiscardedCards = discardCards(activePlayer, currentTurn, supply);

		currentTurn.addExtraMoney(numberOfDiscardedCards);

		return true;
	}


	private int discardCards(Player activePlayer, Turn currentTurn, Supply supply) {
		int numberOfDiscardedCards = 0;

		while (activePlayer.getHandSize() > 0) {
			DiscardCardForCoinDecision decision = new DiscardCardForCoinDecision(activePlayer.getHand());
			activePlayer.decide(decision, this);
			if (decision.isCanceled()) {
				break;
			}
			activePlayer.discardCardsFromHand(decision.getAnswer(), currentTurn, supply);
			numberOfDiscardedCards++;
		}
		return numberOfDiscardedCards;
	}
}
