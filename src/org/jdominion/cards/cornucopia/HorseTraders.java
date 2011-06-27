package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.cornucopia.DiscardCards;
import org.jdominion.effects.cornucopia.HorseTradersEffect;

public class HorseTraders extends Card {

	public HorseTraders() {
		super("Horse Traders", 4);
		addCardEffect(new AddBuys(1));
		addCardEffect(new AddExtraMoney(3));
		addCardEffect(new DiscardCards(2));
		addCardEffect(new HorseTradersEffect());
	}

}
