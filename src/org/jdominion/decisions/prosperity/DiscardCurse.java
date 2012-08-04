package org.jdominion.decisions.prosperity;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.YesNoDecision;

public class DiscardCurse extends YesNoDecision {

	// TODO: maybe let the user decide which curse to discard
	public DiscardCurse() {
		super("Do you want to discard a curse?");
	}
	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		setAnswer(true);
	}

}
