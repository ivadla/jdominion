package org.jdominion.cards.cornucopia;

import org.jdominion.effects.cornucopia.TrustySteedEffect;

public class TrustySteed extends Prize {

	public TrustySteed() {
		super("Trusty Steed");
		addCardEffect(new TrustySteedEffect());
	}

}
