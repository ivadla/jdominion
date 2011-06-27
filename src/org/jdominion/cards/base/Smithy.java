package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.DrawCards;

public class Smithy extends Card {
	public Smithy() {
		super("Smithy", new DrawCards(3), 4);
	}
}
