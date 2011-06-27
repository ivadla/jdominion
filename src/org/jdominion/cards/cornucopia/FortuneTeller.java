package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.cornucopia.FortuneTellerEffect;

public class FortuneTeller extends Card {

	public FortuneTeller() {
		super("Fortune Teller", 3);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new FortuneTellerEffect());
	}

}
