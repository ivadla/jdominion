package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.prosperity.HoardEffect;

public class Hoard extends Card {
	public Hoard() {
		super("Hoard", 6);
		addCardEffect(new CardEffectTreasure(2));
		addCardEffect(new HoardEffect());
	}
}
