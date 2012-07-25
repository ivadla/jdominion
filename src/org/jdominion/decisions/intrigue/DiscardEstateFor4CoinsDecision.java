package org.jdominion.decisions.intrigue;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.YesNoDecision;

public class DiscardEstateFor4CoinsDecision extends YesNoDecision {

	public DiscardEstateFor4CoinsDecision() {
		super("Do you want to discard an estate?");
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		setAnswer(true);
	}

}
