package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.cornucopia.MenagerieEffect;

public class Menagerie extends Card {

	public Menagerie() {
		super("Menagerie", 3);
		addCardEffect(new AddActions(1));
		addCardEffect(new MenagerieEffect());
	}

}
