package org.jdominion;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card.Type;

public class Util {

	public static List<Card> createCardList(Card card) {
		List<Card> cardList = new ArrayList<Card>();
		cardList.add(card);
		return cardList;
	}
	
	public static int countCardsOfType(List<Card> cardList, Type cardType) {
		int counter = 0;
		for(Card card: cardList) {
			if(card.isOfType(cardType)) {
				counter++;
			}
		}
		return counter;
	}
}
