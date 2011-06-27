package org.jdominion.cards.common;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;

public class Copper extends Card {

	public Copper() {
		super("Copper", new CardEffectTreasure(1), 0, false);
	}
}
