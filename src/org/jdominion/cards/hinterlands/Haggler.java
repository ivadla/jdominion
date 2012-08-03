package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.hinterlands.HagglerEffect;

public class Haggler extends Card {

	public Haggler() {
		super("Haggler", 5);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new HagglerEffect());
	}
}
