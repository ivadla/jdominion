package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Hand;
import org.jdominion.Card.Type;

public class ChooseTreasureCardToPlay extends ChooseCardsFromHand {

	public ChooseTreasureCardToPlay(Hand hand) {
		super("Choose a treasure card to play", true, 1, 1, hand);
	}

	@Override
	public boolean isValidAnswer(CardList answer) {
		if (super.isValidAnswer(answer)) {
			if (answer.size() == 1 && answer.getFirst().isOfType(Type.TREASURE)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected Card chooseCardForDefaultAnswer(Hand hand) {
		if (hand.contains(Type.TREASURE)) {
			return hand.getCardsOfType(Type.TREASURE).getFirst();
		} else {
			setCanceled(true);
			return null;
		}
	}

}
