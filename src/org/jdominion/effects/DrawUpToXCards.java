package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.PutInHand;

public class DrawUpToXCards extends CardEffectAction {

	private int maxCardsInHand;
	private boolean canSetAsideActionCards;

	public DrawUpToXCards(int maxCardsInHand, boolean canSetAsideActionCards) {
		this.maxCardsInHand = maxCardsInHand;
		this.canSetAsideActionCards = canSetAsideActionCards;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		CardList setAsideCards = new CardList();
		while (activePlayer.getHandSize() < this.maxCardsInHand) {
			CardList revealedCards = activePlayer.revealCards(1);
			if (revealedCards.isEmpty()) { // no cards left to draw
				break;
			}

			Card revealedCard = revealedCards.getFirst();

			if (revealedCard.isOfType(Type.ACTION) && this.canSetAsideActionCards) {
				PutInHand decision = new PutInHand(revealedCard);
				activePlayer.decide(decision, this);
				if (decision.getAnswer()) {
					activePlayer.addCardToHand(revealedCard);
				} else {
					setAsideCards.add(revealedCard);
				}
			} else {
				activePlayer.addCardToHand(revealedCard);
			}
		}

		activePlayer.placeOnDiscardPile(setAsideCards);

		return true;
	}

}
