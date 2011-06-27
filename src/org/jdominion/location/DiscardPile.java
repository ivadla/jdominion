package org.jdominion.location;

import org.jdominion.Card;
import org.jdominion.Player;

public class DiscardPile extends Location {

	public DiscardPile() {
		super("Discard pile");
	}

	@Override
	public void putCard(Player cardOwner, Card card) {
		cardOwner.placeOnDiscardPile(card);
	}

}
