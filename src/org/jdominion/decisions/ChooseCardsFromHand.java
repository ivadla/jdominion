package org.jdominion.decisions;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;

public abstract class ChooseCardsFromHand extends Decision<List<Card>> {

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
		List<Card> choosenCards;
		if (isAnswered()) {
			choosenCards = new ArrayList<Card>(getAnswer());
		} else {
			choosenCards = new ArrayList<Card>();
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
	public boolean isValidAnswer(List<Card> answer) {
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

	@SuppressWarnings("deprecation")
	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		List<Card> choosenCards = new ArrayList<Card>();
		Hand copyOfHand = new Hand();
		copyOfHand.set(new ArrayList<Card>(hand.getCardList()));
		for (int i = 0; i < getMinimumNumberOfCards(); i++) {
			Card choosenCard = chooseCardForDefaultAnswer(copyOfHand);
			choosenCards.add(choosenCard);
			copyOfHand.remove(choosenCard);
		}
		setAnswer(choosenCards);
	}

	protected abstract Card chooseCardForDefaultAnswer(Hand copyOfHand);

}
