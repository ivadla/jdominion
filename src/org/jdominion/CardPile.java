package org.jdominion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jdominion.effects.CardEffect;

public class CardPile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Card> cards;

	public CardPile(List<Card> cards) {
		this.cards = cards;
	}

	public CardPile(Class<? extends Card> cardClass, int numberOfCardsInPile) throws InstantiationException,
			IllegalAccessException {
		cards = new ArrayList<Card>();
		for (int i = 0; i < numberOfCardsInPile; i++) {
			cards.add(cardClass.newInstance());
		}
	}

	public Class<? extends Card> getCardClass() {
		if (cards.isEmpty()) {
			return null;
		}
		return cards.get(0).getClass();
	}

	public String getCardName() {
		if (cards.isEmpty()) {
			return null;
		}
		return cards.get(0).getName();
	}

	public int getCardCost() {
		assert !cards.isEmpty();
		return cards.get(0).getCost();
	}

	public List<CardEffect> getCardEffects() {
		if (cards.isEmpty()) {
			return new ArrayList<CardEffect>();
		}
		return cards.get(0).getEffects();
	}

	public int getNumberOfCardsInPile() {
		return cards.size();
	}

	public boolean isOfType(Card.Type type) {
		assert !cards.isEmpty();
		return cards.get(0).isOfType(type);
	}

	public Card takeCard() {
		assert !cards.isEmpty();
		return cards.remove(0);
	}
}
