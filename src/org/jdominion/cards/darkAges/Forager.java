package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.TrashCards;
import org.jdominion.effects.darkAges.ForagerEffect;

public class Forager extends Card {

	public Forager() {
		super("Forager", 3);
		addCardEffect(new AddActions(1));
		addCardEffect(new AddBuys(1));
		addCardEffect(new TrashCards(1, 1));
		addCardEffect(new ForagerEffect());
	}
}
