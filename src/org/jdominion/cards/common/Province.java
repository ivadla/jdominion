package org.jdominion.cards.common;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectVictory;

public class Province extends Card {
	public Province() {
		super("Province", new CardEffectVictory(6), 8, false);
	}
}
