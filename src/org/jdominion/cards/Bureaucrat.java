package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.GainCardX;
import org.jdominion.effects.OtherPlayersPutVictoryCardBackOnDeck;
import org.jdominion.location.TopOfDeck;

public class Bureaucrat extends Card {

	public Bureaucrat() {
		super("Bureaucrat", 4);
		addCardEffect(new GainCardX(Silver.class, new TopOfDeck()));
		addCardEffect(new OtherPlayersPutVictoryCardBackOnDeck());
	}

}
