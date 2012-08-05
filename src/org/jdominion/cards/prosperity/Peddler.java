package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.prosperity.PeddlerEffect;

public class Peddler extends Card {

	public Peddler() {
		super("Peddler", 8);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new AddExtraMoney(1));
		addCardEffect(new PeddlerEffect());
	}

}
