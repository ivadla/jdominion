package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.Supply;

public abstract class ChooseCardFromSupply extends Decision<Class<? extends Card>> {

	private Supply availableCards;
	public Supply getAvailableCards() {
		return availableCards;
	}

	public ChooseCardFromSupply(String userMessage, Supply availableCards, boolean cancelable) {
		super(userMessage, cancelable);
		this.availableCards = availableCards;
	}

	@Override
	public boolean isValidAnswer(Class<? extends Card> answer) {
		return availableCards.isCardAvailable(answer);
	}

}
