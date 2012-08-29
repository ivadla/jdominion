package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.GainCardWhichCostsUpToX;
import org.jdominion.location.TopOfDeck;

public class Armory extends Card {

	public Armory() {
		super("Armory", 4);
		addCardEffect(new GainCardWhichCostsUpToX(4, new TopOfDeck()));
	}
}
