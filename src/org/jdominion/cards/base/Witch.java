package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.GiveCurse;

public class Witch extends Card {

	public Witch() {
		super("Witch", 5);
		this.addCardEffect(new DrawCards(2));
		this.addCardEffect(new GiveCurse());
	}
}
