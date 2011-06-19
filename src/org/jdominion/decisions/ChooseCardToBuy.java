package org.jdominion.decisions;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class ChooseCardToBuy extends ChooseCardFromSupply {

	private int availableBuys;
	private int availableCoins;

	public int getAvailableBuys() {
		return availableBuys;
	}

	public int getAvailableCoins() {
		return availableCoins;
	}

	public ChooseCardToBuy(Supply availableCards, int availableBuys, int availableCoins) {
		super("Please choose a card to buy. You have " + availableBuys + " buys and " + availableCoins + " coins.",
				availableCards, true);
		this.availableBuys = availableBuys;
		this.availableCoins = availableCoins;

	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		// TODO we need a way to choose no card here
		setAnswer(getAvailableCards().findMostExpensiveCard());
	}

}
