package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.base.MoatEffect;

public class Moat extends Card {

	public Moat() {
		super("Moat",2);
		addCardEffect(new DrawCards(2));
		addCardEffect(new MoatEffect());
	}
}
