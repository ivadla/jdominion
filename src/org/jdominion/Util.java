package org.jdominion;

import org.jdominion.Card.Type;

public class Util {

	public static int countCardsOfType(CardList cardList, Type cardType) {
		int counter = 0;
		for (Card card : cardList) {
			if (card.isOfType(cardType)) {
				counter++;
			}
		}
		return counter;
	}
}
