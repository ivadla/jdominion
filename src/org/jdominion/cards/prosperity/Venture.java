package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.prosperity.VentureEffect;

public class Venture extends Card {

	public Venture() {
		super("Venture", 5);
		addCardEffect(new CardEffectTreasure(1));
		addCardEffect(new VentureEffect());
	}
}
