package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.cornucopia.RemakeEffect;

public class Remake extends Card {

	public Remake() {
		super("Remake", new RemakeEffect(), 4);
	}

}
