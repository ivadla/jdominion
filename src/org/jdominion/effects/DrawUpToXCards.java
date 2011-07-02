package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
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
		List<Card> setAsideCards = new ArrayList<Card>();
		while (activePlayer.getHandSize() < this.maxCardsInHand) {
			List<Card> revealedCards = activePlayer.revealCards(1);
			if (revealedCards.isEmpty()) { // no cards left to draw
				break;
			}

			Card revealedCard = revealedCards.get(0);

			if (revealedCard.isOfType(Type.ACTION) && this.canSetAsideActionCards) {
				PutInHand decision = new PutInHand(revealedCard);
				activePlayer.decide(decision, this, activePlayer.getHand(), currentTurn, supply);
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
