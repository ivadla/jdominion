package org.jdominion;

import org.jdominion.event.CardGainedFromTrash;
import org.jdominion.event.CardTrashed;
import org.jdominion.event.EventManager;

public class Trash extends CardList {

	@Override
	public void add(Card card) {
		assert false : "Do not add cards directly to the trash. Use Player.trashCard()";
		throw new UnsupportedOperationException("Do not add cards directly to the trash. Use Player.trashCard()");
	}

	@Override
	public void remove(Card removedCard) {
		assert false : "Do not remove cards directly from the trash. Use gainFromTrash() instead";
		throw new UnsupportedOperationException("Do not remove cards directly from the trash. Use gainFromTrash() instead");
	}

	protected void trashCard(Card card, Turn currentTurn, Supply supply) {
		super.add(card);
		EventManager.getInstance().handleEvent(new CardTrashed(card.getOwner(), card, currentTurn, supply));
		card.setOwner(null);
	}

	public void gainFromTrash(Player gainingPlayer, Card cardToGain, Turn currentTurn, Supply supply) {
		super.remove(cardToGain);
		EventManager.getInstance().handleEvent(new CardGainedFromTrash(cardToGain, gainingPlayer, currentTurn, supply));
	}

}
