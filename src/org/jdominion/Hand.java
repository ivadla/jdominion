package org.jdominion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Card> cardsInHand = new ArrayList<Card>();

	/**
	 * Don't call this method directly use player.addCardsToHand instead
	 * 
	 * @param Cards
	 *            to add to the hand
	 */
	public void addAll(List<Card> cards) {
		cardsInHand.addAll(cards);
	}

	public List<Card> getCardList() {
		// return a new list to hopefully prevent ConcurrentModificationException
		// additionally it prevents messing with the hand contents 
		return new ArrayList<Card>(cardsInHand);
	}

	/**
	 * Use player.drawNewHand() instead
	 * 
	 * @deprecated
	 * @param newHandCards
	 */
	public void set(List<Card> newHandCards) {
		assert cardsInHand.size() == 0 : "Hand has to be empty to prevent the loss of cards";
		cardsInHand = newHandCards;
	}

	public Card getCardByClass(Class<? extends Card> cardtoGet) {
		for (Card card : cardsInHand) {
			if (card.getClass() == cardtoGet) {
				return card;
			}
		}
		return null;
	}

	/**
	 * Don't call this method directly use player.removeCardFromHand instead
	 * 
	 * @param card
	 *            to remove
	 */
	public void remove(Card card) {
		cardsInHand.remove(card);
	}

	public boolean contains(Card card) {
		return cardsInHand.contains(card);
	}

	public boolean contains(Card.Type type) {
		for (Card card : cardsInHand) {
			if (card.isOfType(type)) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(Class<? extends Card> cardtoLookFor) {
		for (Card card : cardsInHand) {
			if (card.getClass() == cardtoLookFor) {
				return true;
			}
		}
		return false;
	}

	public int countCard(Class<? extends Card> cardtoCount) {
		int counter = 0;
		for (Card card : cardsInHand) {
			if (card.getClass() == cardtoCount) {
				counter++;
			}
		}
		return counter;
	}

	public int countCoins() {
		int amountOfCoins = 0;
		for (Card card : cardsInHand) {
			amountOfCoins += card.getCoins();
		}
		return amountOfCoins;
	}

	public int size() {
		return cardsInHand.size();
	}

	public Card getCheapestCard() {
		int costOfCheapestCard = Integer.MAX_VALUE;
		Card cheapestCard = null;
		for (Card card : getCardList()) {
			if (card.getCost() < costOfCheapestCard) {
				costOfCheapestCard = card.getCost();
				cheapestCard = card;
			}
		}
		return cheapestCard;
	}
	
	public List<Card> getCardsOfType(Card.Type type) {
		List<Card> cardsOfType = new ArrayList<Card>();
		for (Card card : getCardList()) {
			if (card.isOfType(type)) {
				cardsOfType.add(card);
			}
		}
		return cardsOfType;
	}

}
