package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.Card.Type;
import org.jdominion.CardList;
import org.jdominion.Hand;
import org.jdominion.cards.common.Copper;

public class ChooseCardsFromHandToDiscard extends ChooseCardsFromHand {

	public ChooseCardsFromHandToDiscard(boolean cancelable, int minimumNumberOfCards, int maximumNumberOfCards, Hand hand) {
		super(getMessage("", cancelable), cancelable, minimumNumberOfCards, maximumNumberOfCards, hand);
	}

	protected ChooseCardsFromHandToDiscard(String extraMessage, boolean cancelable, int minimumNumberOfCards, int maximumNumberOfCards, Hand hand) {
		super(getMessage(extraMessage, cancelable), cancelable, minimumNumberOfCards, maximumNumberOfCards, hand);
	}

	protected static String getMessage(String extraMessage, boolean cancelable) {
		String message = "Choose a card to discard";
		if (extraMessage.length() > 0) {
			message += " " + extraMessage;
		}
		if (cancelable) {
			message += " or press cancel";
		}
		message += "!";
		return message;
	}

	@Override
	protected Card chooseCardForDefaultAnswer(Hand hand) {
		if (this.isCancelable()) {
			this.setCanceled(true);
		}
		for (Card card : hand) {
			if (card.isOfType(Type.CURSE)) {
				return card;
			} else if (card.getTypes().size() == 1 && card.isOfType(Type.VICTORY)) {
				return card;
			}
		}

		if (hasSuperfluousActions(hand)) {
			return chooseCheapestTerminalAction(hand);
		}

		if (hand.contains(Copper.class)) {
			return hand.getCardByClass(Copper.class);
		}
		return hand.getCheapestCard();
	}

	// TODO: move to a more general place
	protected boolean hasSuperfluousActions(Hand hand) {
		CardList actionCards = hand.getCardsOfType(Type.ACTION);

		if (actionCards.size() > 1) {
			if ((countProvidedActions(actionCards) + 1) < actionCards.size()) {
				return true;
			}
		}
		return false;
	}

	// TODO: move to a more general place
	protected Card chooseCheapestTerminalAction(CardList actionCards) {
		int costOfCheapestActionCard = Integer.MAX_VALUE;
		Card cheapestActionCard = null;
		for (Card card : actionCards) {
			if (card.isTerminalAction() && card.getCost() < costOfCheapestActionCard) {
				costOfCheapestActionCard = card.getCost();
				cheapestActionCard = card;
			}
		}
		return cheapestActionCard;
	}

	// TODO: move to a more general place
	private int countProvidedActions(CardList cards) {
		int actions = 0;
		for (Card card : cards) {
			actions += card.getAddedActions();
		}
		return actions;
	}

}
