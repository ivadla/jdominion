package org.jdominion.cards.darkAges.ruins;

import org.jdominion.effects.AddExtraMoney;

public class AbandonedMine extends Ruins {

	public AbandonedMine() {
		super("Abandoned Mine", new AddExtraMoney(1));
	}
}
