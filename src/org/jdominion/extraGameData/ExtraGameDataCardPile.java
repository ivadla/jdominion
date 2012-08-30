package org.jdominion.extraGameData;

import org.jdominion.CardPile;

public abstract class ExtraGameDataCardPile extends ExtraGameData<CardPile> {

	private CardPile pile;

	protected ExtraGameDataCardPile(String name, CardPile pile) {
		super(name, true);
		this.pile = pile;
	}

	@Override
	public void set(CardPile data) {
		throw new UnsupportedOperationException("You can't change this pile");
	
	}

	@Override
	public CardPile get() {
		return this.pile;
	}

	@Override
	public void add(CardPile data) {
		throw new UnsupportedOperationException("You can't change this pile");
	}

	@Override
	public String getContentsAsString() {
		return Integer.toString(pile.getNumberOfCardsInPile());
	}

}
