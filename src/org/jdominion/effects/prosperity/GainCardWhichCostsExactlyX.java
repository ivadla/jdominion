package org.jdominion.effects.prosperity;

import org.jdominion.CardPile;
import org.jdominion.effects.CardEffectGainCard;

public class GainCardWhichCostsExactlyX extends CardEffectGainCard {

	private int cost;

	public GainCardWhichCostsExactlyX(int cost) {
		super();
		this.cost = cost;
	}

	@Override
	protected boolean isCardPilePermitted(CardPile pile) {
		return pile.getCardCost() == this.cost;
	}

}
