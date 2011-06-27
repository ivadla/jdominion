package org.jdominion.cards;

import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.DiademEffect;

public class Diadem extends Prize {

	public Diadem() {
		super("Diadem");
		addCardEffect(new CardEffectTreasure(2));
		addCardEffect(new DiademEffect());
	}

}
