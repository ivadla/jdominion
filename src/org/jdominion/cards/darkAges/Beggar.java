package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.darkAges.BeggarGainCopperEffect;
import org.jdominion.effects.darkAges.BeggarReactionEffect;

public class Beggar extends Card {

	public Beggar() {
		super("Beggar", 2);
		addCardEffect(new BeggarGainCopperEffect());
		addCardEffect(new BeggarReactionEffect());
	}
}
