package org.jdominion.effects.base;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.base.DiscardDeckDecision;
import org.jdominion.effects.CardEffectAction;

public class DiscardDeckEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		DiscardDeckDecision decision = new DiscardDeckDecision();
		activePlayer.decide(decision, this);

		if (decision.getAnswer()) {
			CardList deck = activePlayer.getCardsFromDeck(activePlayer.getDeckSize());
			activePlayer.placeOnDiscardPile(deck);
		}

		return true;
	}

}
