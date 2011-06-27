package org.jdominion.aiStrategies;

import org.jdominion.cards.base.Witch;

public class WitchStrategy extends BuyOneTypeOfActionCard {

	public WitchStrategy() {
		this(2);
	}

	public WitchStrategy(int numOfWitchesToBuy) {
		super(Witch.class, numOfWitchesToBuy);
	}

}
