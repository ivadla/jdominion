package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.cornucopia.TournamentEffect;

public class Tournament extends Card {

	public Tournament() {
		super("Tournament", 4);
		addCardEffect(new AddActions(1));
		addCardEffect(new TournamentEffect());
	}
	
}
