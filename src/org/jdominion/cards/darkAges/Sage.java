package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.darkAges.SageEffect;

public class Sage extends Card {

	public Sage() {
		super("Sage", 3);
		addCardEffect(new AddActions(1));
		addCardEffect(new SageEffect());
	}
}
