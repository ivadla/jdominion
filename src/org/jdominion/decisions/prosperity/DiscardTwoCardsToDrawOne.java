package org.jdominion.decisions.prosperity;

import org.jdominion.Hand;
import org.jdominion.decisions.ChooseCardsFromHandToDiscard;

public class DiscardTwoCardsToDrawOne extends ChooseCardsFromHandToDiscard {

	public DiscardTwoCardsToDrawOne(Hand hand) {
		super(true, 2, 2, hand);
	}

	@Override
	public String getUserMessage() {
		return "Choose two cards to discard to draw a new one or press cancel";
	}
}
