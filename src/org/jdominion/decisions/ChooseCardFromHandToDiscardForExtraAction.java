package org.jdominion.decisions;

import org.jdominion.Hand;

public class ChooseCardFromHandToDiscardForExtraAction extends ChooseCardFromHandToDiscard {

	public ChooseCardFromHandToDiscardForExtraAction(Hand hand) {
		super("for +1 Action", true, hand);
	}

}
