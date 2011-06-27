package org.jdominion.aiStrategies;

import org.jdominion.cards.base.Smithy;

public class SmithyStrategy extends BuyOneTypeOfActionCard {

	public SmithyStrategy() {
		this(2);
	}

	public SmithyStrategy(int numOfSmithiesToBuy) {
		super(Smithy.class, numOfSmithiesToBuy);
	}

}
