package org.jdominion.decisions.prosperity;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Card.Type;
import org.jdominion.cards.common.Copper;
import org.jdominion.decisions.ChooseCardFromHandToDiscard;

public class DiscardCardForCoinDecision extends ChooseCardFromHandToDiscard {

	@Override
	protected Card chooseCardForDefaultAnswer(Hand hand) {
		for (Card card : hand) {
			if (card.isOfType(Type.CURSE)) {
				return card;
			} else if (card.getTypes().size() == 1 && card.isOfType(Type.VICTORY)) {
				return card;
			}
		}

		if (hasSuperfluousActions(hand)) {
			return chooseCheapestTerminalAction(hand);
		}

		if (hand.contains(Copper.class)) {
			return hand.getCardByClass(Copper.class);
		}
		setCanceled(true);
		return hand.getCheapestCard();
	}

	public DiscardCardForCoinDecision(Hand hand) {
		super("for a coin", true, hand);
	}

}
