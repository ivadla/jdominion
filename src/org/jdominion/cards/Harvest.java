package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.HarvestEffect;

public class Harvest extends Card {

	public Harvest() {
		super("Harvest", new HarvestEffect(), 5);
	}

}
