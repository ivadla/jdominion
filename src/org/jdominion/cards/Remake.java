package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.RemakeEffect;

public class Remake extends Card {

	public Remake() {
		super("Remake", new RemakeEffect(), 4);
	}

}
