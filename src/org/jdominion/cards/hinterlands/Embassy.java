package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.DiscardCards;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.hinterlands.EmbassyOnGainEffect;

public class Embassy extends Card {
	public Embassy() {
		super("Embassy", 5);
		addCardEffect(new DrawCards(5));
		addCardEffect(new DiscardCards(3));
		addCardEffect(new EmbassyOnGainEffect());
	}
}
