package org.jdominion.extraGameData;

import org.jdominion.CardList;
import org.jdominion.CardPile;
import org.jdominion.cards.darkAges.Spoils;

public class SpoilsPile extends ExtraGameDataCardPile {

	private static final int NUMBER_OF_SPOILS_IN_GAME = 15;
	public SpoilsPile() {
		super("Spoils", createSpoilsPile());
	}

	private static CardPile createSpoilsPile() {
		CardList spoilsList = new CardList();
		for (int i = 0; i < NUMBER_OF_SPOILS_IN_GAME; i++) {
			spoilsList.add(new Spoils());
		}
		CardPile pile = new CardPile(spoilsList);
		return pile;
	}

}
