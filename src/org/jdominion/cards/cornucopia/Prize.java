package org.jdominion.cards.cornucopia;

import org.jdominion.Card;

public abstract class Prize extends Card {

	public Prize(String name) {
		super(name, 0, false);
		getTypes().add(Type.PRIZE);
	}

}
