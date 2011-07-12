package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Hand;
import org.jdominion.Card.Type;

public class ChooseActionCardToPlay extends ChooseCardsFromHand {

	public ChooseActionCardToPlay(Hand hand, boolean cancelable) {
		super("Choose an action card to play", cancelable, 1, 1, hand);
	}

	public ChooseActionCardToPlay(Hand hand) {
		super("Choose an action card to play", true, 1, 1, hand);
	}

	@Override
	public boolean isValidAnswer(CardList answer) {
		if (super.isValidAnswer(answer)) {
			if (answer.size() == 1 && answer.getFirst().isOfType(Type.ACTION)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected Card chooseCardForDefaultAnswer(Hand hand) {
		for (Card card : hand.getCardList()) {
			if (card.isOfType(Type.ACTION) && !card.isTerminalAction()) {
				return card;
			}
		}
		for (Card card : hand.getCardList()) {
			if (card.isOfType(Type.ACTION)) {
				return card;
			}
		}
		setCanceled(true);
		return null;
	}

}
