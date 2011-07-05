package org.jdominion.event;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Util;

public class CardsTrashed extends Event {

	private List<Card> trashedCards;

	public List<Card> getTrashedCards() {
		return trashedCards;
	}

	public CardsTrashed(Player cardsOwner, List<Card> trashedCards) {
		super(cardsOwner.getName() + " trashes " + Util.convertCardListToString(trashedCards) + ".", cardsOwner);
		this.trashedCards = trashedCards;
	}

}
