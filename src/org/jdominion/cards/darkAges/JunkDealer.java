package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.TrashCards;

public class JunkDealer extends Card {

	public JunkDealer() {
		super("Junk Dealer", 5);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new AddExtraMoney(1));
		addCardEffect(new TrashCards(1, 1));
	}
}
