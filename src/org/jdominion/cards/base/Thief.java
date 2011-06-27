package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.base.StealTreasureCards;

public class Thief extends Card {
	public Thief() {
		super("Thief", 4);
		addCardEffect(new StealTreasureCards());
	}
}
