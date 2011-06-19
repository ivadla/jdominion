package org.jdominion.decisions;

import org.jdominion.Hand;

public class ChooseCardFromHandToDiscardForExtraBuy extends ChooseCardFromHandToDiscard {

	public ChooseCardFromHandToDiscardForExtraBuy(Hand hand) {
		super("for +1 Buy", true, hand);
	}

}
