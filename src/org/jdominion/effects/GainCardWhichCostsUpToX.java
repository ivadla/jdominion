package org.jdominion.effects;

import org.jdominion.CardPile;

public class GainCardWhichCostsUpToX extends CardEffectGainCard {

	private int maximumCost;

	public GainCardWhichCostsUpToX(int maximumCost) {
		super();
		this.maximumCost = maximumCost;
	}

	@Override
	protected boolean isCardPilePermitted(CardPile pile) {
		return pile.getCardCost() <= maximumCost;
	}

}
