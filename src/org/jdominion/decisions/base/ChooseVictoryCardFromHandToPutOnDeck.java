package org.jdominion.decisions.base;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseCardsFromHand;

public class ChooseVictoryCardFromHandToPutOnDeck extends ChooseCardsFromHand {

	public ChooseVictoryCardFromHandToPutOnDeck(Hand hand) {
		super("Choose a victory card to put back on your deck", false, 1, 1, hand);
	}

	@Override
	protected boolean isValidCard(Card card) {
		return card.isOfType(Type.VICTORY);
	}

	@Override
	protected Card chooseCardForDefaultAnswer(Hand hand) {
		for (Card card : hand) {
			if (card.getTypes().size() == 1 && card.getTypes().get(0) == Type.VICTORY) {
				return card;
			}
		}

		if (hand.contains(Type.VICTORY)) {
			return hand.getCardsOfType(Type.VICTORY).getFirst();
		}
		assert false : "no victory card in hand";
		return null;
	}

}
