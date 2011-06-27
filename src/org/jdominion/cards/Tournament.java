package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.TournamentEffect;

public class Tournament extends Card {

	public Tournament() {
		super("Tournament", 4);
		addCardEffect(new AddActions(1));
		addCardEffect(new TournamentEffect());
	}
	
}