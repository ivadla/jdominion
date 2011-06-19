package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.StealTreasureCards;

public class Thief extends Card {
	public Thief() {
		super("Thief", 4);
		addCardEffect(new StealTreasureCards());
	}
}
