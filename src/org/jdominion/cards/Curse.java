package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectCurse;

public class Curse extends Card {

	public Curse() {
		super("Curse",new CardEffectCurse(-1), 0, false);
	}
}
