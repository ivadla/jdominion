package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.prosperity.TalismanEffect;

public class Talisman extends Card {

	public Talisman() {
		super("Talisman", 4);
		addCardEffect(new CardEffectTreasure(1));
		addCardEffect(new TalismanEffect());
	}
}
