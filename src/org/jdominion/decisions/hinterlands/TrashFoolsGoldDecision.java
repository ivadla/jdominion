package org.jdominion.decisions.hinterlands;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.YesNoDecision;

public class TrashFoolsGoldDecision extends YesNoDecision {

	public TrashFoolsGoldDecision() {
		super("Do you want to trash a Fool's Gold from your Hand?");
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		setAnswer(false);
	}

}
