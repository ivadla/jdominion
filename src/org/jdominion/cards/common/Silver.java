package org.jdominion.cards.common;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;

public class Silver extends Card {

	public Silver() {
		super("Silver", new CardEffectTreasure(2), 3, false);
	}
}
