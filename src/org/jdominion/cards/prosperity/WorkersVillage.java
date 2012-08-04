package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.DrawCards;

public class WorkersVillage extends Card {

	public WorkersVillage() {
		super("Worker's Village", 4);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(2));
		addCardEffect(new AddBuys(1));
	}
}
