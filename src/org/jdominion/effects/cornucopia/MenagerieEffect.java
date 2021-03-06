package org.jdominion.effects.cornucopia;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectAction;

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
		for (Card card : hand) {
			if (hand.countCard(card.getClass()) > 1) {
				return true;
			}
		}

		return false;
	}

}
