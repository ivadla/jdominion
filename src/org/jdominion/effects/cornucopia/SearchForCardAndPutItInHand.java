package org.jdominion.effects.cornucopia;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectAction;

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

		CardList setAsideCards = new CardList();

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
