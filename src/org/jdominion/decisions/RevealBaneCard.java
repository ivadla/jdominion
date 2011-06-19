package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class RevealBaneCard extends YesNoDecision {

	public RevealBaneCard(Card baneCard) {
		super("Do you want to reveal a " + baneCard.getName());
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		this.setAnswer(true);
	}

}
