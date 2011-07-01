package org.jdominion.decisions;

import org.jdominion.Hand;

public class ChooseCardFromHandToDiscard extends ChooseCardsFromHandToDiscard {

	public ChooseCardFromHandToDiscard(boolean cancelable, Hand hand) {
		super(cancelable, 1, 1, hand);
	}
	
	protected ChooseCardFromHandToDiscard(String extraMessage, boolean cancelable, Hand hand) {
		super(extraMessage, cancelable, 1, 1, hand);
	}

}
