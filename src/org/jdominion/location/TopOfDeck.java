package org.jdominion.location;

import org.jdominion.Card;
import org.jdominion.Player;

public class TopOfDeck extends Location {

	public TopOfDeck() {
		super("Top of the deck");
	}

	@Override
	public void putCard(Player cardOwner, Card card) {
		cardOwner.placeOnDeck(card);
	}

}
