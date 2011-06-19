package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.GainCardWhichCostsUpToX;
import org.jdominion.effects.TrashThisCard;

public class Feast extends Card {

	public Feast() {
		super("Feast", 4);
		addCardEffect(new TrashThisCard());
		addCardEffect(new GainCardWhichCostsUpToX(5));
	}
}
