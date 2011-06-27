package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;

public class RemodelEffect extends TrashGainEffect {

	private int addedValue = 2;

	public RemodelEffect(int addedValue) {
		super();
		this.addedValue = addedValue;
	}

	@Override
	protected ChooseCardsFromHandToTrash createDecision(Hand hand) {
		return new ChooseCardsFromHandToTrash(false, 1, 1, hand);
	}

	@Override
	protected Supply createSupply(Supply supply, Card cardToTrash) {
		return supply.createSupplyWithMaximumCost(cardToTrash.getCost()
				+ addedValue);
	}

}
