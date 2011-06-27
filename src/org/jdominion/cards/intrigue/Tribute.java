package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.intrigue.TributeEffect;

public class Tribute extends Card {

	public Tribute() {
		super("Tribute", new TributeEffect(), 5);
	}

}
