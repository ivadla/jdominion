package org.jdominion.aiStrategies;

import org.jdominion.cards.Smithy;

public class SmithyStrategy extends BuyOneTypeOfActionCard {

	public SmithyStrategy() {
		this(2);
	}

	public SmithyStrategy(int numOfSmithiesToBuy) {
		super(Smithy.class, numOfSmithiesToBuy);
	}

}
