package org.jdominion.cards.common;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectVictory;

public class Estate extends Card {

	public Estate() {
		super("Estate", new CardEffectVictory(1), 2, false);
	}
}
