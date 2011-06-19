package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectVictory;

public class Duchy extends Card {
	public Duchy() {
		super("Duchy", new CardEffectVictory(3), 5, false);
	}
}
