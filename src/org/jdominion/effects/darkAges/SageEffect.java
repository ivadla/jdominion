package org.jdominion.effects.darkAges;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectAction;

public class SageEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		CardList revealedCards = new CardList();
		Card revealedCard;
		while ((revealedCard = activePlayer.revealCard()) != null) {
			if (revealedCard.getCost() >= 3) {
				activePlayer.addCardToHand(revealedCard);
				activePlayer.placeOnDiscardPile(revealedCards);
				return true;
			} else {
				revealedCards.add(revealedCard);
			}
		}
		activePlayer.placeOnDiscardPile(revealedCards);
		return false;
	}

}
