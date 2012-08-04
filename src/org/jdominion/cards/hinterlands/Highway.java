package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.ReduceCostsOfCards;

public class Highway extends Card {

	public Highway() {
		super("Highway", 5);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new ReduceCostsOfCards(1));
	}
}
