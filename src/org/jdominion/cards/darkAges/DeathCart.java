package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.darkAges.DeathCardTrashEffect;
import org.jdominion.effects.darkAges.DeathCartOnGainEffect;
import org.jdominion.effects.darkAges.LooterEffect;

public class DeathCart extends Card {

	public DeathCart() {
		super("Death Cart", 4);
		addCardEffect(new AddExtraMoney(5));
		addCardEffect(new DeathCardTrashEffect());
		addCardEffect(new DeathCartOnGainEffect());
		addCardEffect(new LooterEffect());
	}
}
