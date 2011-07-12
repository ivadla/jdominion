package org.jdominion.event;

import org.jdominion.CardList;
import org.jdominion.Player;

public class CardsTrashed extends Event {

	private CardList trashedCards;

	public CardList getTrashedCards() {
		return trashedCards;
	}

	public CardsTrashed(Player cardsOwner, CardList trashedCards) {
		super(cardsOwner.getName() + " trashes " + trashedCards.convertToString() + ".", cardsOwner);
		this.trashedCards = trashedCards;
	}

}
