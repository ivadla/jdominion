package org.jdominion.decisions;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class RevealProvince extends YesNoDecision {

	public RevealProvince() {
		super("Do you want to reveal a Province");
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		setAnswer(true);
	}

}
