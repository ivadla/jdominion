package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.base.CellarEffect;

public class Cellar extends Card {
	public Cellar() {
		super("Cellar", 2);
		this.addCardEffect(new AddActions(1));
		this.addCardEffect(new CellarEffect());
	}

}
