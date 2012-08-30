package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.ReturnThisCard;

public class Spoils extends Card {

	public Spoils() {
		super("Spoils", 0, false);
		addCardEffect(new CardEffectTreasure(3));
		addCardEffect(new ReturnThisCard());
	}
}
