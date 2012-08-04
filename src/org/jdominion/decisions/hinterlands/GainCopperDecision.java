package org.jdominion.decisions.hinterlands;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.YesNoDecision;

public class GainCopperDecision extends YesNoDecision {

	public GainCopperDecision() {
		super("Do you want to gain a copper?");
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		setAnswer(false);
	}

}
