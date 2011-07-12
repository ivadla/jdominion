package org.jdominion;

public class Deck extends CardList {


	public Deck() {
		super();
	}

	/**
	 * returns the top card and removes it from the deck
	 * 
	 * @return
	 */
	public Card getTopCard() {
		return elements.remove(size() - 1);
	}

	public void putOnTop(Card c) {
		add(c);
	}

}
