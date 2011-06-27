package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.cornucopia.JesterEffect;

public class Jester extends Card {

	public Jester() {
		super("Jester", 5);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new JesterEffect());
	}

}
