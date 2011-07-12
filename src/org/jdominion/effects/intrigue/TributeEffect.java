package org.jdominion.effects.intrigue;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectAction;

public class TributeEffect extends CardEffectAction {
	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		Player nextPlayer = currentTurn.getNextPlayer();
		CardList revealedCards = nextPlayer.revealCards(2);
		if (revealedCards.isEmpty()) {
			return false;
		}
		List<Class<? extends Card>> alreadyProcessedCards = new ArrayList<Class<? extends Card>>();
		for (Card revealedCard : revealedCards) {
			if (!alreadyProcessedCards.contains(revealedCard.getClass())) {
				processTypes(activePlayer, currentTurn, revealedCard);
				alreadyProcessedCards.add(revealedCard.getClass());
			}
		}

		nextPlayer.placeOnDiscardPile(revealedCards);
		return true;
	}

	private void processTypes(Player activePlayer, Turn currentTurn, Card revealedCard) {
		for (Card.Type cardType : revealedCard.getTypes()) {
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
	}
}
