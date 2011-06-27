package org.jdominion.aiStrategies;

import org.jdominion.cards.seaside.Cutpurse;

public class OnlyCutpurse extends BuyOneTypeOfActionCard {

	public OnlyCutpurse() {
		this(1);
	}

	public OnlyCutpurse(int numOfCutpursesToBuy) {
		super(Cutpurse.class, numOfCutpursesToBuy);
	}

}
