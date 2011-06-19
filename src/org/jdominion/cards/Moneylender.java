package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.TrashCopper;

public class Moneylender extends Card {
	public Moneylender() {
		super("Moneylender", 4);
		addCardEffect(new TrashCopper());
		addCardEffect(new AddExtraMoney(3));
		setEffectsDependOnEachOther(true);
	}
}
