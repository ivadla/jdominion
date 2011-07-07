package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.intrigue.StewardEffect;

public class Steward extends Card {

	public Steward() {
		super("Steward", 3);
		addCardEffect(new StewardEffect());
	}
}
