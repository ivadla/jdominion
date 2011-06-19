package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Card.Type;

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
		for (Card card : hand.getCardList()) {
			if (card.getTypes().size() == 1 && card.getTypes().get(0) == Type.VICTORY) {
				return card;
			}
		}
		for (Card card : hand.getCardList()) {
			if (card.isOfType(Type.VICTORY)) {
				return card;
			}
		}
		return null;
	}

}
