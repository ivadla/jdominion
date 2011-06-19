package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.AddExtraMoney;

public class Woodcutter extends Card {

	public Woodcutter() {
		super("Woodcutter", 3);
		addCardEffect(new AddBuys(1));
		addCardEffect(new AddExtraMoney(2));
	}
}
