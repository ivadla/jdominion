package org.jdominion.decisions;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Card.Type;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.CardEffect;

public class ChooseActionCardToPlay extends ChooseCardsFromHand {

	public ChooseActionCardToPlay(Hand hand, boolean cancelable) {
		super("Choose an action card to play", cancelable, 1, 1, hand);
	}
	
	public ChooseActionCardToPlay(Hand hand) {
		super("Choose an action card to play", true, 1, 1, hand);
	}

	@Override
	public boolean isValidAnswer(List<Card> answer) {
		if (super.isValidAnswer(answer)) {
			if (answer.size() == 1 && answer.get(0).isOfType(Type.ACTION)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected Card chooseCardForDefaultAnswer(Hand hand) {
		for (Card card : hand.getCardList()) {
			for (CardEffect effect : card.getEffects()) {
				if (effect instanceof AddActions) {
					return card;
				}
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
