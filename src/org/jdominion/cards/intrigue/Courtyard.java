package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.PutCardFromHandOnTopOfDeck;

public class Courtyard extends Card {

	public Courtyard() {
		super("Courtyard", 2);
		addCardEffect(new DrawCards(3));
		addCardEffect(new PutCardFromHandOnTopOfDeck());
	}
}
