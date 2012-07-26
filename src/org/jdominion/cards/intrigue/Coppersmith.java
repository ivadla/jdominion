package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.intrigue.CoppersmithEffect;

public class Coppersmith extends Card {

	public Coppersmith() {
		super("Coppersmith", 4);
		this.addCardEffect(new CoppersmithEffect());
	}
}
