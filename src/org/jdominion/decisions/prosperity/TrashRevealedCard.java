package org.jdominion.decisions.prosperity;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.decisions.YesNoDecision;

public class TrashRevealedCard extends YesNoDecision {

	private Card revealedCard; 
	
	public Card getRevealedCard() {
		return revealedCard;
	}

	public TrashRevealedCard(Card revealedCard) {
		super("Trash " + revealedCard.getName() + "?");
		this.revealedCard = revealedCard;
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		if(revealedCard.getClass() == Copper.class) {
			setAnswer(true);
		} else {
			setAnswer(false);
		}
	}

}
