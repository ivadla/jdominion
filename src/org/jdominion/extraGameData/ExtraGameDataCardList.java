package org.jdominion.extraGameData;

import org.jdominion.CardList;

public class ExtraGameDataCardList extends ExtraGameData<CardList> {

	private CardList cards;

	public ExtraGameDataCardList(String name, boolean isVisibleByAllPlayers) {
		super(name, isVisibleByAllPlayers);
		cards = new CardList();
	}

	@Override
	public void add(CardList data) {
		cards.addAll(data);
	}

	@Override
	public CardList get() {
		return cards;
	}

	@Override
	public String getContentsAsString() {
		return cards.convertToString();
	}

	@Override
	public void set(CardList data) {
		assert cards.isEmpty() : "Possible losing track of cards " + getContentsAsString();
		this.cards = data;
	}

}
