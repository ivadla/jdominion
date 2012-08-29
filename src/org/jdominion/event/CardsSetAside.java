package org.jdominion.event;

import org.jdominion.CardList;
import org.jdominion.Player;

//TODO: make this a CardEvent
public class CardsSetAside extends Event {

	private CardList cardsSetAside;

	public CardList getCardsSetAside() {
		return cardsSetAside;
	}

	public CardsSetAside(Player cardOwner, CardList cardsSetAside) {
		super(cardOwner.getName() + " sets " + cardsSetAside.convertToString() + " aside.", cardOwner);
		this.cardsSetAside = cardsSetAside;
	}

}
