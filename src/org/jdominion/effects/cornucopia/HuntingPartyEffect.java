package org.jdominion.effects.cornucopia;

import org.jdominion.Card;
import org.jdominion.Hand;

public class HuntingPartyEffect extends SearchForCardAndPutItInHand {

	public HuntingPartyEffect() {
		super(true);
	}

	@Override
	protected boolean isCorrectCard(Card card, Hand hand) {
		return !hand.contains(card.getClass());
	}

}
