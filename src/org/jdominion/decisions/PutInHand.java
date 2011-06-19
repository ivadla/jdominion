package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class PutInHand extends YesNoDecision {

	private Card revealedCard;

	public PutInHand(Card revealedCard) {
		super("Do you want to put " + revealedCard.getName() + " into your hand?");
		this.revealedCard = revealedCard;
	}

	public Card getRevealedCard() {
		return revealedCard;
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		if (currentTurn.getAvailableActions() > 0) {
			setAnswer(true);
		}
		setAnswer(false);

	}

}
