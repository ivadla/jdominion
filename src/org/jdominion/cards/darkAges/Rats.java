package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.GainCardX;
import org.jdominion.effects.darkAges.OnTrashDrawXCardsEffect;
import org.jdominion.effects.darkAges.RatsTrashEffect;

public class Rats extends Card {

	public Rats() {
		super("Rats", 4);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new GainCardX(Rats.class));
		addCardEffect(new RatsTrashEffect());
		addCardEffect(new OnTrashDrawXCardsEffect(1));

	}
}
