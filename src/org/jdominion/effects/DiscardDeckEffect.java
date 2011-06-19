package org.jdominion.effects;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.DiscardDeckDecision;

public class DiscardDeckEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		DiscardDeckDecision decision = new DiscardDeckDecision();
		activePlayer.decide(decision, this, activePlayer.getHand(), currentTurn, supply);

		if (decision.getAnswer()) {
			List<Card> deck = activePlayer.getCardsFromDeck(activePlayer.getDeckSize());
			activePlayer.placeOnDiscardPile(deck);
		}

		return true;
	}

}
