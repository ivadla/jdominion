package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;

public abstract class ChooseCardsFromHand extends Decision<CardList> {

	private int minimumNumberOfCards;
	private int maximumNumberOfCards;
	private Hand hand;

	public int getMinimumNumberOfCards() {
		return minimumNumberOfCards;
	}

	public int getMaximumNumberOfCards() {
		return maximumNumberOfCards;
	}

	public boolean addAnswer(Card choosenCard) {
		CardList choosenCards;
		if (isAnswered()) {
			choosenCards = new CardList(getAnswer());
		} else {
			choosenCards = new CardList();
		}
		choosenCards.add(choosenCard);
		if (isValidAnswer(choosenCards)) {
			setAnswer(choosenCards);
			return true;
		}
		return false;
	}

	public ChooseCardsFromHand(String message, boolean cancelable, int minimumNumberOfCards, int maximumNumberOfCards, Hand hand) {
		super(message, cancelable);
		this.minimumNumberOfCards = minimumNumberOfCards;
		this.maximumNumberOfCards = maximumNumberOfCards;
		this.hand = hand;
	}

	@Override
	public boolean isValidAnswer(CardList answer) {
		if (answer.size() < minimumNumberOfCards) {
			return false;
		}
		if (answer.size() > maximumNumberOfCards) {
			return false;
		}
		for (Card card : answer) {
			if (!hand.contains(card)) {
				return false;
			} else if (!isValidCard(card)) {
				return false;
			}
		}
		return true;
	}

	// to be overwritten by subclasses
	protected boolean isValidCard(Card card) {
		return true;
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		CardList choosenCards = new CardList();
		Hand copyOfHand = new Hand(hand);
		for (int i = 0; i < getMinimumNumberOfCards(); i++) {
			Card choosenCard = chooseCardForDefaultAnswer(copyOfHand);
			choosenCards.add(choosenCard);
			copyOfHand.remove(choosenCard);
		}
		setAnswer(choosenCards);
	}

	protected abstract Card chooseCardForDefaultAnswer(Hand copyOfHand);

}
