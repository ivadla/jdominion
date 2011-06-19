package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.TributeEffect;

public class Tribute extends Card {

	public Tribute() {
		super("Tribute", new TributeEffect(), 5);
	}

}
