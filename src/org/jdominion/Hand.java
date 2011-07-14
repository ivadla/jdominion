package org.jdominion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Hand extends CardList implements Serializable {

	private static final long serialVersionUID = 1L;

	public Hand() {
		super();
	}

	public Hand(CardList initialContent) {
		super(initialContent);
	}

	/**
	 * Don't call this method directly use player.addCardToHand instead
	 * 
	 * @param cards
	 *            Cards to add to the hand
	 */
	@Override
	public void add(Card e) {
		super.add(e);
	}

	/**
	 * Don't call this method directly use player.addCardsToHand instead
	 * 
	 * @param cards
	 *            Cards to add to the hand
	 */
	@Override
	public void addAll(CardList cards) {
		super.addAll(cards);
	}

	/**
	 * Don't call this method directly use player.addCardsToHand instead
	 * 
	 * @param cards
	 *            Cards to add to the hand
	 */
	@Override
	public void addAll(Collection<? extends Card> c) {
		super.addAll(c);
	}

	/**
	 * returns an iterator over an unmodifiable copy of the hand use the add and remove method from the hand class
	 * instead of modifying the iterator
	 */
	@Override
	public Iterator<Card> iterator() {
		return Collections.unmodifiableCollection(new ArrayList<Card>(elements)).iterator();
	}

	/**
	 * Don't call this method directly use player.removeCardFromHand instead
	 * 
	 * @param card
	 *            to remove
	 */
	public void remove(Card card) {
		elements.remove(card);
	}

}
