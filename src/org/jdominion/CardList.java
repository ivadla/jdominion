package org.jdominion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CardList implements Iterable<Card> {

	protected List<Card> elements;

	public CardList() {
		this.elements = new ArrayList<Card>();
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

	public boolean disjoint(CardList otherList) {
		return disjoint(otherList.elements);
	}

	public boolean disjoint(Collection<Card> otherList) {
		return Collections.disjoint(elements, otherList);
	}

	// Methods from List:

	public boolean add(Card e) {
		assert !elements.contains(e) : "Adding duplicated Card to the list. This is probably a bug";
		return elements.add(e);
	}

	/**
	 * This method is deprecated. Use addAll(CardList c) instead
	 * 
	 * @param c
	 * @return
	 */
	@Deprecated
	public boolean addAll(Collection<? extends Card> c) {
		return elements.addAll(c);
	}

	public boolean addAll(CardList c) {
		return elements.addAll(c.elements);
	}

	public boolean contains(Object o) {
		return elements.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return elements.containsAll(c);
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public Iterator<Card> iterator() {
		return elements.iterator();
	}

	public boolean remove(Object o) {
		return elements.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return elements.removeAll(c);
	}

	public int size() {
		return elements.size();
	}
}
