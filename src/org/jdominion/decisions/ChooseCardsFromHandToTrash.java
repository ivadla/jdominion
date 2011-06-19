package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.cards.Copper;
import org.jdominion.cards.Curse;
import org.jdominion.cards.Estate;

public class ChooseCardsFromHandToTrash extends ChooseCardsFromHand {

	public ChooseCardsFromHandToTrash(boolean cancelable, int minimumNumberOfCards, int maximumNumberOfCards, Hand hand) {
		super("Choose a Card to Trash", cancelable, minimumNumberOfCards, maximumNumberOfCards, hand);
	}

	protected ChooseCardsFromHandToTrash(String message, boolean cancelable, int minimumNumberOfCards,
			int maximumNumberOfCards, Hand hand) {
		super(message, cancelable, minimumNumberOfCards, maximumNumberOfCards, hand);
	}

	@Override
	protected Card chooseCardForDefaultAnswer(Hand hand) {
		if (hand.contains(Curse.class)) {
			return hand.getCardByClass(Curse.class);
		} else if (hand.contains(Estate.class)) {
			return hand.getCardByClass(Estate.class);
		} else if (hand.contains(Copper.class)) {
			return hand.getCardByClass(Copper.class);
		}

		Card cheapestCard = hand.getCheapestCard();
		return cheapestCard;
	}
}
