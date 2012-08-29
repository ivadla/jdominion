package org.jdominion.cards.darkAges.ruins;

import org.jdominion.Card;
import org.jdominion.effects.CardEffect;

public abstract class Ruins extends Card {

	public Ruins(String name, CardEffect effect) {
		super(name, effect, 0, false);
		addType(Type.RUINS);
	}

}
