package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.DiscardCards;
import org.jdominion.effects.DrawCards;

public class Oasis extends Card {

	public Oasis() {
		super("Oasis", 3);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new AddExtraMoney(1));
		addCardEffect(new DiscardCards(1));
	}
}
