package org.jdominion.cards.common;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;

public class Gold extends Card {

	public Gold() {
		super("Gold", new CardEffectTreasure(3), 6, false);
	}
}
