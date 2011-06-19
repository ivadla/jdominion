package org.jdominion.decisions;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class ChooseCardToGain extends ChooseCardFromSupply {

	public ChooseCardToGain(Supply availableCards) {
		super("Please choose a card to gain.", availableCards, false);
	}
	
	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		setAnswer(getAvailableCards().findMostExpensiveCard());
	}

}
