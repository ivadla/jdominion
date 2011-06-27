package org.jdominion.cards.cornucopia;

import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.cornucopia.DiademEffect;

public class Diadem extends Prize {

	public Diadem() {
		super("Diadem");
		addCardEffect(new CardEffectTreasure(2));
		addCardEffect(new DiademEffect());
	}

}
