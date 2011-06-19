package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.DiscardDeckEffect;

public class Chancellor extends Card {

	public Chancellor() {
		super("Chancellor", 3);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new DiscardDeckEffect());
	}

}
