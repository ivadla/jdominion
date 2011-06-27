package org.jdominion.location;

import org.jdominion.Card;
import org.jdominion.Player;

public class Hand extends Location {

	public Hand() {
		super("Hand");
	}

	@Override
	public void putCard(Player cardOwner, Card card) {
		cardOwner.addCardToHand(card);
	}

}
