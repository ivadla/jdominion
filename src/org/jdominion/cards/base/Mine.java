package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.base.MineEffect;

public class Mine extends Card {

	public Mine() {
		super("Mine", new MineEffect(3), 5);
	}

}
