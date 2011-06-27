package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.FarmingVillageEffect;

public class FarmingVillage extends Card {

	public FarmingVillage() {
		super("Farming Village", 4);
		addCardEffect(new AddActions(2));
		addCardEffect(new FarmingVillageEffect());
	}

}
