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
	protected CardList cards;
	private Class<? extends Card> classOfCardsInPile;

	public CardPile(CardList cards) {
		this.cards = cards;
		classOfCardsInPile = cards.getFirst().getClass();
		assert cards.countCard(classOfCardsInPile) == cards.size() : "all cards in the pile must have the same class";
	}

	public CardPile(Class<? extends Card> cardClass, int numberOfCardsInPile) throws InstantiationException, IllegalAccessException {
		this.classOfCardsInPile = cardClass;
		cards = new CardList();
		for (int i = 0; i < numberOfCardsInPile; i++) {
			cards.add(cardClass.newInstance());
		}
	}

	public Class<? extends Card> getCardClass() {
		return classOfCardsInPile;
	}

	public String getCardName() {
		return CardClassInfo.getInstance().getName(classOfCardsInPile);
	}

	public int getCardCost() {
		return CardClassInfo.getInstance().getCost(classOfCardsInPile);
	}

	// TODO: maybe use CardClassInfo for this
	public List<CardEffect> getCardEffects() {
		if (cards.isEmpty()) {
			return new ArrayList<CardEffect>();
		}
		return cards.getFirst().getEffects();
	}

	public int getNumberOfCardsInPile() {
		return cards.size();
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public boolean isOfType(Card.Type type) {
		return CardClassInfo.getInstance().isOfType(classOfCardsInPile, type);
	}

	public Card takeCard() {
		assert !cards.isEmpty();
		Card card = cards.getFirst();
		cards.remove(card);
		return card;
	}
}
