package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.base.AdventurerEffect;

public class Adventurer extends Card {

	public Adventurer() {
		super("Adventurer", new AdventurerEffect(), 6);
	}
}
