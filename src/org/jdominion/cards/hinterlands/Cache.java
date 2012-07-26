package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.hinterlands.CacheOnGainEffect;

public class Cache extends Card {
	public Cache() {
		super("Cache", 5);
		addCardEffect(new CardEffectTreasure(3));
		addCardEffect(new CacheOnGainEffect());
	}
}
