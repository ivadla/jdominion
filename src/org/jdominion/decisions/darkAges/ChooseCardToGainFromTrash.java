package org.jdominion.decisions.darkAges;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseOneCardFromRevealedCards;
import org.jdominion.decisions.revealedCards.Gain;

public class ChooseCardToGainFromTrash extends ChooseOneCardFromRevealedCards {

	public ChooseCardToGainFromTrash(CardList revealedCards) {
		super("Choose a card to gain from the trash", false, revealedCards, Gain.getInstance());
	}

	@Override
	protected Card defaultCard(Turn currentTurn, CardList revealedCards) {
		return revealedCards.getMostExpensiveCard();
	}

}
