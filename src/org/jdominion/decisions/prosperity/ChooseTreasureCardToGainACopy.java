package org.jdominion.decisions.prosperity;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseCardsFromHand;

public class ChooseTreasureCardToGainACopy extends ChooseCardsFromHand {
	public ChooseTreasureCardToGainACopy(Hand hand) {
		super("Reveal a treasure card to gain a copy of it.", true, 1, 1, hand);
	}

	@Override
	protected Card chooseCardForDefaultAnswer(Hand copyOfHand) {
		return copyOfHand.getCardsOfType(Type.TREASURE).getMostExpensiveCard();
	}

	@Override
	protected boolean isValidCard(Card card) {
		return card.isOfType(Type.TREASURE);
	}
}
