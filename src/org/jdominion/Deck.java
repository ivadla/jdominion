package org.jdominion;

public class Deck extends CardList {


	public Deck() {
		super();
	}

	/**
	 * returns the top card and removes it from the deck
	 * 
	 * @return null, if the deck is empty
	 */
	public Card getTopCard() {
		if (isEmpty()) {
			return null;
		}
		return elements.remove(size() - 1);
	}

	public void putOnTop(Card c) {
		add(c);
	}

}
