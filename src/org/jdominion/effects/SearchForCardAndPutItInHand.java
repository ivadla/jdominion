package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public abstract class SearchForCardAndPutItInHand extends CardEffectAction {

	private boolean revealHand;

	protected SearchForCardAndPutItInHand(boolean revealHand) {
		this.revealHand = revealHand;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (this.revealHand) {
			activePlayer.revealHand();
		}

		List<Card> setAsideCards = new ArrayList<Card>();

		Card revealedCard = activePlayer.revealCard();

		while (revealedCard != null && !isCorrectCard(revealedCard, activePlayer.getHand())) {
			setAsideCards.add(revealedCard);
			revealedCard = activePlayer.revealCard();
		}

		if (revealedCard != null) {
			activePlayer.addCardToHand(revealedCard);
		}

		activePlayer.placeOnDiscardPile(setAsideCards);

		return true;
	}

	protected abstract boolean isCorrectCard(Card card, Hand hand);

}
