package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;

public class Village extends Card {

	public Village() {
		super("Village", 3);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(2));
	}
}
