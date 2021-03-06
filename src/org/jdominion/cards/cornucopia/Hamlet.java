package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.cornucopia.HamletEffect;

public class Hamlet extends Card {

	public Hamlet() {
		super("Hamlet", 2);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new HamletEffect());
	}

}
