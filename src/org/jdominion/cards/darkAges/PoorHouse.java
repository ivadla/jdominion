package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.darkAges.PoorHouseEffect;

public class PoorHouse extends Card {

	public PoorHouse() {
		super("Poor House", 1);
		addCardEffect(new AddExtraMoney(4));
		addCardEffect(new PoorHouseEffect());
	}
}
