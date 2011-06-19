package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DiscardTopCard;
import org.jdominion.effects.DrawCards;

public class Spy extends Card {

	public Spy() {
		super("Spy", 4);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new DiscardTopCard());
	}

}
