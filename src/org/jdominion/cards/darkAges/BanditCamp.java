package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.darkAges.GainSpoils;

public class BanditCamp extends Card {

	public BanditCamp() {
		super("Bandit Camp", 5);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(2));
		addCardEffect(new GainSpoils(1));
	}
}
