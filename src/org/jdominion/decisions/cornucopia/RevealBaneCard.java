package org.jdominion.decisions.cornucopia;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.YesNoDecision;

public class RevealBaneCard extends YesNoDecision {

	public RevealBaneCard(Card baneCard) {
		super("Do you want to reveal a " + baneCard.getName());
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		this.setAnswer(true);
	}

}
