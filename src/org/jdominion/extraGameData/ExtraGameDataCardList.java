package org.jdominion.extraGameData;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Util;

public class ExtraGameDataCardList extends ExtraGameData<List<Card>> {

	private List<Card> cards;

	public ExtraGameDataCardList(String name, boolean isVisibleByAllPlayers) {
		super(name, isVisibleByAllPlayers);
		cards = new ArrayList<Card>();
	}

	@Override
	public void add(List<Card> data) {
		cards.addAll(data);
	}

	@Override
	public List<Card> get() {
		return cards;
	}

	@Override
	public String getContentsAsString() {
		return Util.convertCardListToString(cards);
	}

	@Override
	public void set(List<Card> data) {
		assert cards.size() == 0 : "Possible losing track of cards " + getContentsAsString();
		this.cards = data;
	}

}
