package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectVictory;
import org.jdominion.effects.intrigue.NoblesEffect;

public class Nobles extends Card {

	public Nobles() {
		super("Nobles", new NoblesEffect(), 6);
		addCardEffect(new CardEffectVictory(2));
	}
}
