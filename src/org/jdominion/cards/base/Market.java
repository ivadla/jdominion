package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.DrawCards;

public class Market extends Card {

	public Market() {
		super("Market", 5);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new AddBuys(1));
		addCardEffect(new AddExtraMoney(1));
	}
}
