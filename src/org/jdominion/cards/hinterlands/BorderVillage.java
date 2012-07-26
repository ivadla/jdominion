package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.hinterlands.BorderVillageOnGainEffect;

public class BorderVillage extends Card {

	public BorderVillage() {
		super("Border Village", 6);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(2));
		addCardEffect(new BorderVillageOnGainEffect());
	}
}
