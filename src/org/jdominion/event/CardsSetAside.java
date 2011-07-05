package org.jdominion.event;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Util;

public class CardsSetAside extends Event {

	private List<Card> cardsSetAside;

	public List<Card> getCardsSetAside() {
		return cardsSetAside;
	}

	public CardsSetAside(Player cardOwner, List<Card> cardsSetAside) {
		super(cardOwner.getName() + " sets " + Util.convertCardListToString(cardsSetAside) + " aside.", cardOwner);
		this.cardsSetAside = cardsSetAside;
	}

}
