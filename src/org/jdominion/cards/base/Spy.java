package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.base.DiscardTopCard;

public class Spy extends Card {

	public Spy() {
		super("Spy", 4);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new DiscardTopCard());
	}

}
