package org.jdominion.cards;

import org.jdominion.effects.AddActions;
import org.jdominion.effects.GainCardX;
import org.jdominion.location.TopOfDeck;

public class BagOfGold extends Prize {

	public BagOfGold() {
		super("Bag of Gold");
		addCardEffect(new AddActions(1));
		addCardEffect(new GainCardX(Gold.class, new TopOfDeck()));
	}

}
