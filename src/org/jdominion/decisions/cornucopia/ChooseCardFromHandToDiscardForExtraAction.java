package org.jdominion.decisions.cornucopia;

import org.jdominion.Hand;
import org.jdominion.decisions.ChooseCardFromHandToDiscard;

public class ChooseCardFromHandToDiscardForExtraAction extends ChooseCardFromHandToDiscard {

	public ChooseCardFromHandToDiscardForExtraAction(Hand hand) {
		super("for +1 Action", true, hand);
	}

}
