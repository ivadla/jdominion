package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.cornucopia.HarvestEffect;

public class Harvest extends Card {

	public Harvest() {
		super("Harvest", new HarvestEffect(), 5);
	}

}
