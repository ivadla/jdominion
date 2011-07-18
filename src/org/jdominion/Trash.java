package org.jdominion;

import org.jdominion.event.CardsRemovedFromTrash;
import org.jdominion.event.CardsTrashed;
import org.jdominion.event.EventManager;

public class Trash extends CardList {

	@Override
	public void add(Card card) {
		super.add(card);
		EventManager.getInstance().handleEvent(new CardsTrashed(card.getOwner(), new CardList(card)));
		card.setOwner(null);
	}

	@Override
	public void remove(Card removedCard) {
		super.remove(removedCard);
		EventManager.getInstance().handleEvent(new CardsRemovedFromTrash(new CardList(removedCard)));
	}

}
