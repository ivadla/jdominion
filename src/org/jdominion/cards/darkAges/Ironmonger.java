package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.darkAges.IronmongerEffect;

public class Ironmonger extends Card {

	public Ironmonger() {
		super("Ironmonger", 4);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new IronmongerEffect());
	}
}
