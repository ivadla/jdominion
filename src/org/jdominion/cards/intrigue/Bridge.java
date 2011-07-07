package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.intrigue.BridgeEffect;

public class Bridge extends Card {

	public Bridge() {
		super("Bridge", 4);
		addCardEffect(new AddBuys(1));
		addCardEffect(new AddExtraMoney(1));
		addCardEffect(new BridgeEffect());
	}

}
