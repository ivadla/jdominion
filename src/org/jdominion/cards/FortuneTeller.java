package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.FortuneTellerEffect;

public class FortuneTeller extends Card {

	public FortuneTeller() {
		super("Fortune Teller", 3);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new FortuneTellerEffect());
	}

}
