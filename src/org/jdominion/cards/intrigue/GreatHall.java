package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.CardEffectVictory;
import org.jdominion.effects.DrawCards;

public class GreatHall extends Card {
	public GreatHall() {
		super("Great Hall", 3);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new CardEffectVictory(1));
	}
}
