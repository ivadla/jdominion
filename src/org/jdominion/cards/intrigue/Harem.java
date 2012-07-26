package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.CardEffectVictory;

public class Harem extends Card {
	public Harem() {
		super("Harem", 6);
		addCardEffect(new CardEffectTreasure(2));
		addCardEffect(new CardEffectVictory(2));
	}
}
