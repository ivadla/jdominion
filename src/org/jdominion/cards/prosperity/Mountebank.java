package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.prosperity.MountebankEffect;

public class Mountebank extends Card {
	public Mountebank() {
		super("Mountebank", 5);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new MountebankEffect());
	}
}
