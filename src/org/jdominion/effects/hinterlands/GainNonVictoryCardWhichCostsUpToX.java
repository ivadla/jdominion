package org.jdominion.effects.hinterlands;

import org.jdominion.CardPile;
import org.jdominion.Card.Type;
import org.jdominion.effects.GainCardWhichCostsUpToX;

public class GainNonVictoryCardWhichCostsUpToX extends GainCardWhichCostsUpToX {

	public GainNonVictoryCardWhichCostsUpToX(int maximumCost) {
		super(maximumCost);
	}

	@Override
	protected boolean isCardPilePermitted(CardPile pile) {
		if (pile.isOfType(Type.VICTORY)) {
			return false;
		}
		return super.isCardPilePermitted(pile);
	}

}
