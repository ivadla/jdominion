package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AdventurerEffect;

public class Adventurer extends Card {

	public Adventurer() {
		super("Adventurer", new AdventurerEffect(), 6);
	}
}
