package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.darkAges.CultistOnTrashEffect;
import org.jdominion.effects.darkAges.LooterEffect;
import org.jdominion.effects.darkAges.OtherPlayersGainRuins;
import org.jdominion.effects.darkAges.PlayAnotherCultistEffect;

public class Cultist extends Card {

	public Cultist() {
		super("Cultist", 5);
		addCardEffect(new DrawCards(2));
		addCardEffect(new PlayAnotherCultistEffect());
		addCardEffect(new LooterEffect());
		addCardEffect(new OtherPlayersGainRuins());
		addCardEffect(new CultistOnTrashEffect());
	}
}
