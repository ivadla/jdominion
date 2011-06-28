package org.jdominion.decisions.cornucopia;

import org.jdominion.Hand;
import org.jdominion.decisions.ChooseCardFromHandToDiscard;

public class ChooseCardFromHandToDiscardForExtraBuy extends ChooseCardFromHandToDiscard {

	public ChooseCardFromHandToDiscardForExtraBuy(Hand hand) {
		super("for +1 Buy", true, hand);
	}

}
