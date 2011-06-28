package org.jdominion.decisions.base;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.YesNoDecision;

public class DiscardDeckDecision extends YesNoDecision {

	public DiscardDeckDecision() {
		super("Do you want to discard your deck?");
	}
	
	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		setAnswer(false);
	}

}
