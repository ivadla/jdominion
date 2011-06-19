package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class MenagerieEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		activePlayer.revealHand();

		if (containsDuplicateCard(activePlayer.getHand())) {
			activePlayer.drawCardsIntoHand(1);
		} else {
			activePlayer.drawCardsIntoHand(3);
		}

		return false;
	}

	private boolean containsDuplicateCard(Hand hand) {
		for (Card card : hand.getCardList()) {
			if (hand.countCard(card.getClass()) > 1) {
				return true;
			}
		}

		return false;
	}

}
