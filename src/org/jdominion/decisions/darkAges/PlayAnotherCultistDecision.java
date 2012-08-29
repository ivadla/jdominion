package org.jdominion.decisions.darkAges;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.YesNoDecision;

public class PlayAnotherCultistDecision extends YesNoDecision {

	public PlayAnotherCultistDecision() {
		super("Do you want to play another cultist?");
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		setAnswer(true);
	}

}
