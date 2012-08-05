package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectVictory;
import org.jdominion.effects.hinterlands.FarmlandEffect;

public class Farmland extends Card {

	public Farmland() {
		super("Farmland", 6);
		addCardEffect(new CardEffectVictory(2));
		addCardEffect(new FarmlandEffect());
	}
}
