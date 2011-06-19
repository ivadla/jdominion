package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.MoatEffect;

public class Moat extends Card {

	public Moat() {
		super("Moat",2);
		addCardEffect(new DrawCards(2));
		addCardEffect(new MoatEffect());
	}
}
