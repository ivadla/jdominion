package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.GainCardWhichCostsUpToX;

public class Workshop extends Card {

	public Workshop() {
		super("Workshop", new GainCardWhichCostsUpToX(4), 3);
	}
}
