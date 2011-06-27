package org.jdominion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

import org.jdominion.Card.Type;
import org.jdominion.cards.common.*;

public class CardFactory {

	public static int getNumberOfVictoryCards(int numberOfPlayers) {
		if (numberOfPlayers < 4) {
			return numberOfPlayers * 4;
		} else {
			return 12;
		}

	}

	public static int getNumberOfCurseCards(int numberOfPlayers) {
		return (numberOfPlayers - 1) * 10;
	}

	private static int getNumberOfCardsForPile(Class<? extends Card> cardClass, int numberOfPlayers)
			throws InstantiationException, IllegalAccessException {
		if (cardClass == Curse.class) {
			return getNumberOfCurseCards(numberOfPlayers);
		} else if (cardClass == Copper.class) {
			return 60;
		} else if (cardClass == Silver.class) {
			return 40;
		} else if (cardClass == Gold.class) {
			return 30;
		} else if (cardClass.newInstance().isOfType(Type.VICTORY)) {
			return getNumberOfVictoryCards(numberOfPlayers);
		} else {
			return 10;
		}
	}

	public static Supply createSupply(int numberOfPlayers) {
		return createSupply(numberOfPlayers, new ArrayList<Class<? extends Card>>());
	}

	public static Supply createSupply(int numberOfPlayers, List<Class<? extends Card>> cardsToInclude) {

		List<Class<? extends Card>> kingdomCards = get10RandomKingdomCards(cardsToInclude);
		List<CardPile> piles = new ArrayList<CardPile>();

		piles.add(createCardPile(Curse.class, numberOfPlayers));
		piles.add(createCardPile(Estate.class, numberOfPlayers));
		piles.add(createCardPile(Duchy.class, numberOfPlayers));
		piles.add(createCardPile(Province.class, numberOfPlayers));
		piles.add(createCardPile(Copper.class, numberOfPlayers));
		piles.add(createCardPile(Silver.class, numberOfPlayers));
		piles.add(createCardPile(Gold.class, numberOfPlayers));

		for (Class<? extends Card> cardClass : sortCardsByPrice(kingdomCards)) {
			piles.add(createCardPile(cardClass, numberOfPlayers));
		}

		return new Supply(piles);
	}

	private static List<Class<? extends Card>> get10RandomKingdomCards(List<Class<? extends Card>> cardsToInclude) {
		List<Class<? extends Card>> allCards = ClassFinder.findAllKingdomCards();
		Collections.shuffle(allCards);
		List<Class<? extends Card>> tenCards = new ArrayList<Class<? extends Card>>(cardsToInclude);
		for (int i = 0; tenCards.size() < 10; i++) {
			if (!tenCards.contains(allCards.get(i))) {
				tenCards.add(allCards.get(i));
			}
		}
		return tenCards;
	}

	private static List<Class<? extends Card>> sortCardsByPrice(List<Class<? extends Card>> cards) {
		Collections.sort(cards, new Comparator<Class<? extends Card>>() {

			@Override
			public int compare(Class<? extends Card> cardClass1, Class<? extends Card> cardClass2) {
				try {
					Card card1 = cardClass1.newInstance();
					Card card2 = cardClass2.newInstance();
					if (card1.getCost() < card2.getCost()) {
						return -1;
					} else if (card1.getCost() > card2.getCost()) {
						return 1;
					} else {
						return card1.getName().compareToIgnoreCase(card2.getName());
					}
				} catch (InstantiationException e) {
					throw new RuntimeException("Error while sorting cards", e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Error while sorting cards", e);
				}
			}
		});
		return cards;
	}

	public static CardPile createCardPile(Class<? extends Card> cardClass, int numberOfPlayers) {
		try {

			return new CardPile(cardClass, getNumberOfCardsForPile(cardClass, numberOfPlayers));

		} catch (InstantiationException e) {
			throw new RuntimeException("Error while creating CardPile for card " + cardClass.getSimpleName(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error while creating CardPile for card " + cardClass.getSimpleName(), e);
		}
	}

	public static Deque<Card> createInitialDeck() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 7; i++) {
			deck.add(new Copper());
		}
		for (int i = 0; i < 3; i++) {
			deck.add(new Estate());
		}
		Collections.shuffle(deck);
		return new ArrayDeque<Card>(deck);
	}
}
