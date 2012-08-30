package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.darkAges.GainSpoils;
import org.jdominion.effects.darkAges.LooterEffect;
import org.jdominion.effects.darkAges.OtherPlayersGainRuins;

public class Marauder extends Card {

	public Marauder() {
		super("Marauder", 4);
		addCardEffect(new GainSpoils(1));
		addCardEffect(new OtherPlayersGainRuins());
		addCardEffect(new LooterEffect());
	}
}
