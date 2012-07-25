package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.intrigue.ConspiratorEffect;

public class Conspirator extends Card {

	public Conspirator() {
		super("Conspirator", 4);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new ConspiratorEffect());
	}

}
