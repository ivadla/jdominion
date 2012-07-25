package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.intrigue.BaronEffect;

public class Baron extends Card {

	public Baron() {
		super("Baron", 4);
		addCardEffect(new AddBuys(1));
		addCardEffect(new BaronEffect());
	}

}
