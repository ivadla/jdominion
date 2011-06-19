package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.DiscardCopper;

public class Cutpurse extends Card {

	public Cutpurse() {
		super("Cutpurse", 4);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new DiscardCopper());
	}
}
