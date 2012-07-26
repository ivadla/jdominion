package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.Hand;

public class ChooseCardFromHandToPutOnDeck extends ChooseCardsFromHand {

	public ChooseCardFromHandToPutOnDeck(Hand hand) {
		super("Choose a card to put back on your deck", false, 1, 1, hand);
	}

	@Override
	protected Card chooseCardForDefaultAnswer(Hand hand) {
		return hand.getCheapestCard();
	}
}
