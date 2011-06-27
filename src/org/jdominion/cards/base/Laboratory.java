package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;

public class Laboratory extends Card {

	public Laboratory() {
		super("Laboratory", 5);
		addCardEffect(new DrawCards(2));
		addCardEffect(new AddActions(1));
	}
}
