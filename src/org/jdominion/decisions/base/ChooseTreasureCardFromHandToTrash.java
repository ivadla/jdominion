package org.jdominion.decisions.base;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Card.Type;
import org.jdominion.cards.common.Copper;
import org.jdominion.cards.common.Silver;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;

public class ChooseTreasureCardFromHandToTrash extends ChooseCardsFromHandToTrash {

	public ChooseTreasureCardFromHandToTrash(boolean cancelable, Hand hand) {
		super("Choose a treasure card to trash", cancelable, 1, 1, hand);
	}

	@Override
	protected Card chooseCardForDefaultAnswer(Hand hand) {
		if (hand.contains(Copper.class)) {
			return hand.getCardByClass(Copper.class);
		} else if (hand.contains(Silver.class)) {
			return hand.getCardByClass(Silver.class);
		}
		for (Card card : hand.getCardList()) {
			if (card.isOfType(Type.TREASURE)) {
				return card;
			}
		}
		assert false : "There are no treasure cards in the hand";
		return hand.getCardList().get(0);
	}

	@Override
	protected boolean isValidCard(Card card) {
		return card.isOfType(Type.TREASURE);
	}

}
