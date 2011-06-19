package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class TributeEffect extends CardEffectAction {
	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		Player nextPlayer = currentTurn.getNextPlayer();
		List<Card> revealedCards = nextPlayer.revealCards(2);
		if (revealedCards.size() == 0) {
			return false;
		}
		List<Card.Type> typesOfRevealedCards = new ArrayList<Card.Type>(revealedCards.get(0).getTypes());
		if (revealedCards.size() == 2 && !revealedCards.get(0).getClass().equals(revealedCards.get(1).getClass())) {
			typesOfRevealedCards.addAll(revealedCards.get(1).getTypes());
		}
		for (Card.Type cardType : typesOfRevealedCards) {
			switch (cardType) {
			case ACTION:
				currentTurn.addActions(2);
				break;
			case TREASURE:
				currentTurn.addExtraMoney(2);
				break;
			case VICTORY:
				activePlayer.drawCardsIntoHand(2);
				break;
			default:
				break;
			}
		}
		nextPlayer.placeOnDiscardPile(revealedCards);
		return true;
	}
}
