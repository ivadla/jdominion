package org.jdominion.cards.seaside;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.seaside.DiscardCopper;

public class Cutpurse extends Card {

	public Cutpurse() {
		super("Cutpurse", 4);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new DiscardCopper());
	}
}
