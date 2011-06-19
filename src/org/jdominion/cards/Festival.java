package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.AddExtraMoney;

public class Festival extends Card {

	public Festival() {
		super("Festival", 5);
		addCardEffect(new AddActions(2));
		addCardEffect(new AddBuys(1));
		addCardEffect(new AddExtraMoney(2));
	}
}
