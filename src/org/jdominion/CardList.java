package org.jdominion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.jdominion.Card.Type;
import org.jdominion.effects.CardEffect;

public class CardList implements Iterable<Card> {

	protected List<Card> elements;
	private boolean mayContainDuplicatedElements = false;

	public CardList() {
		this.elements = new ArrayList<Card>();
	}

	public CardList(boolean mayContainDuplicatedElements) {
		this();
		this.mayContainDuplicatedElements = mayContainDuplicatedElements;
	}

	public CardList(Card initialContent) {
		this();
		this.add(initialContent);
	}

	public CardList(CardList initialContent) {
		this(initialContent.elements);
	}

	public CardList(List<Card> initialContent) {
		this.elements = new ArrayList<Card>(initialContent);
	}

	public CardList(Card[] initialContent) {
		this.elements = new ArrayList<Card>(Arrays.asList(initialContent));
	}

	/**
	 * Shuffle this cardlist
	 */
	public void shuffle() {
		Collections.shuffle(elements);
	}

	public void sortCardsByName() {
		Collections.sort(elements, new Comparator<Card>() {
			@Override
			public int compare(Card card1, Card card2) {
				return card1.getName().compareToIgnoreCase(card2.getName());
			}
		});
	}

	public String convertToString() {
		String message = "";
		if (isEmpty()) {
			message += "no cards";
		} else {
			for (int i = 0; i < elements.size() - 1; i++) {
				message += elements.get(i).getName() + ", ";
			}
			message += elements.get(elements.size() - 1).getName();
		}
		return message;
	}

	/**
	 * @return the first element or null if this CardList is empty
	 */
	public Card getFirst() {
		if (isEmpty()) {
			return null;
		} else {
			return elements.get(0);
		}
	}

	/**
	 * @return the last element or null if this CardList is empty
	 */
	public Card getLast() {
		if (isEmpty()) {
			return null;
		} else {
			return elements.get(elements.size() - 1);
		}
	}

	public boolean disjoint(CardList otherList) {
		return disjoint(otherList.elements);
	}

	public boolean disjoint(Collection<Card> otherList) {
		return Collections.disjoint(elements, otherList);
	}

	// Methods from List:

	public void add(Card e) {
		if (!this.mayContainDuplicatedElements) {
			assert !elements.contains(e) : "Adding duplicated Card to the list. This is probably a bug";
		}
		elements.add(e);
	}

	public void addAsFirst(Card e) {
		if (!this.mayContainDuplicatedElements) {
			assert !elements.contains(e) : "Adding duplicated Card to the list. This is probably a bug";
		}
		elements.add(0, e);
	}

	/**
	 * This method is deprecated. Use addAll(CardList c) instead
	 * 
	 * @param c
	 * @return
	 */
	@Deprecated
	public void addAll(Collection<? extends Card> c) {
		elements.addAll(c);
	}

	public void addAll(CardList c) {
		for (Card cardToAdd : c) {
			this.add(cardToAdd);
		}
	}

	public boolean contains(Card o) {
		return elements.contains(o);
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public Iterator<Card> iterator() {
		return elements.iterator();
	}

	public void remove(Card o) {
		elements.remove(o);
	}

	public int size() {
		return elements.size();
	}

	public Card getCardByClass(Class<? extends Card> cardtoGet) {
		for (Card card : elements) {
			if (card.getClass() == cardtoGet) {
				return card;
			}
		}
		return null;
	}

	public boolean contains(Card.Type type) {
		for (Card card : elements) {
			if (card.isOfType(type)) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(Class<? extends Card> cardtoLookFor) {
		for (Card card : elements) {
			if (card.getClass() == cardtoLookFor) {
				return true;
			}
		}
		return false;
	}

	public int countCard(Class<? extends Card> cardtoCount) {
		int counter = 0;
		for (Card card : elements) {
			if (card.getClass() == cardtoCount) {
				counter++;
			}
		}
		return counter;
	}

	public int countCardsOfType(Type cardType) {
		int counter = 0;
		for (Card card : elements) {
			if (card.isOfType(cardType)) {
				counter++;
			}
		}
		return counter;
	}

	public int countCoins() {
		int amountOfCoins = 0;
		for (Card card : elements) {
			amountOfCoins += card.getCoins();
		}
		return amountOfCoins;
	}

	public Card getCheapestCard() {
		int costOfCheapestCard = Integer.MAX_VALUE;
		Card cheapestCard = null;
		for (Card card : elements) {
			if (card.getCost() < costOfCheapestCard) {
				costOfCheapestCard = card.getCost();
				cheapestCard = card;
			}
		}
		return cheapestCard;
	}

	public Card getMostExpensiveCard() {
		int costOfMostExpensiveCard = Integer.MIN_VALUE;
		Card mostExpensiveCard = null;
		for (Card card : elements) {
			if (card.getCost() > costOfMostExpensiveCard) {
				costOfMostExpensiveCard = card.getCost();
				mostExpensiveCard = card;
			}
		}
		return mostExpensiveCard;
	}

	public CardList getCardsOfType(Card.Type type) {
		CardList cardsOfType = new CardList();
		for (Card card : elements) {
			if (card.isOfType(type)) {
				cardsOfType.add(card);
			}
		}
		return cardsOfType;
	}

	public CardList getCardsByEffectClass(Class<? extends CardEffect> effectToGet) {
		CardList cardsWithEffect = new CardList();
		for (Card card : elements) {
			if (card.hasEffect(effectToGet)) {
				cardsWithEffect.add(card);
			}
		}
		return cardsWithEffect;
	}
}
