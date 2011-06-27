package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.base.DiscardDeckEffect;

public class Chancellor extends Card {

	public Chancellor() {
		super("Chancellor", 3);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new DiscardDeckEffect());
	}

}
