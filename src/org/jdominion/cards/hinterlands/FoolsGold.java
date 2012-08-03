package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.hinterlands.FoolsGoldReactionEffect;
import org.jdominion.effects.hinterlands.FoolsGoldTreasureEffect;

public class FoolsGold extends Card {

	public FoolsGold() {
		super("Fool's Gold", 2);
		addCardEffect(new FoolsGoldTreasureEffect());
		addCardEffect(new FoolsGoldReactionEffect());
	}
}
