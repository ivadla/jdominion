package org.jdominion.decisions.promo;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseOneCardFromRevealedCards;
import org.jdominion.decisions.revealedCards.Discard;

public class ChooseCardToDiscardFromRevealedCards extends ChooseOneCardFromRevealedCards {

	public ChooseCardToDiscardFromRevealedCards(CardList revealedCards) {
		super("Choose a card for your opponent to discard", false, revealedCards, Discard.getInstance());
	}

	@Override
	protected Card defaultCard(Turn currentTurn, CardList revealedCards) {
		// discard the most expensive action card if the player has actions left
		// TODO: should know about golem or double-throne room which could provide actions
		if (currentTurn.getAvailableActions() > 0 && revealedCards.contains(Type.ACTION)) {
			return revealedCards.getCardsOfType(Type.ACTION).getMostExpensiveCard();
		}
		// discard the most expensive treasure
		if (revealedCards.contains(Type.TREASURE)) {
			return revealedCards.getCardsOfType(Type.TREASURE).getMostExpensiveCard();
		}

		// no treasure nor actions: just discard the most expensive card
		return revealedCards.getMostExpensiveCard();
	}

}
