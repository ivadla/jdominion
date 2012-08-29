package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.DiscardCardsForCoinsEffect;
import org.jdominion.effects.base.CellarEffect;

public class Storeroom extends Card {

	public Storeroom() {
		super("Storeroom", 3);
		addCardEffect(new AddBuys(1));
		addCardEffect(new CellarEffect());
		addCardEffect(new DiscardCardsForCoinsEffect());
	}
}
