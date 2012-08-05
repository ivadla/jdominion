package org.jdominion.cards.seaside;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DiscardCards;
import org.jdominion.effects.DrawCards;

public class Warehouse extends Card {

	public Warehouse() {
		super("Warehouse", 3);
		addCardEffect(new DrawCards(3));
		addCardEffect(new AddActions(1));
		addCardEffect(new DiscardCards(3));
	}
}
