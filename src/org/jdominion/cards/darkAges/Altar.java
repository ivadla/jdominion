package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.GainCardWhichCostsUpToX;
import org.jdominion.effects.TrashCards;

public class Altar extends Card {

	public Altar() {
		super("Altar", 6);
		addCardEffect(new TrashCards(1, 1));
		addCardEffect(new GainCardWhichCostsUpToX(5));
	}
}
