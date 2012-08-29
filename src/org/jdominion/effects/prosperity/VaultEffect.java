package org.jdominion.effects.prosperity;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.prosperity.DiscardCardForCoinDecision;
import org.jdominion.decisions.prosperity.DiscardTwoCardsToDrawOne;
import org.jdominion.effects.CardEffectAction;

public class VaultEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		int numberOfDiscardedCards = discardCards(activePlayer, currentTurn, supply);

		currentTurn.addExtraMoney(numberOfDiscardedCards);

		for (Player player : currentTurn.getOtherPlayers()) {
			discardTwoCardsAndDrawOne(player, currentTurn, supply);
		}

		return true;
	}

	private void discardTwoCardsAndDrawOne(Player player, Turn currentTurn, Supply supply) {
		DiscardTwoCardsToDrawOne decision = new DiscardTwoCardsToDrawOne(player.getHand());
		player.decide(decision, this);
		if (!decision.isCanceled()) {
			CardList cardsToDiscard = decision.getAnswer();
			player.discardCardsFromHand(cardsToDiscard, currentTurn, supply);
			if (cardsToDiscard.size() == 2) {
				player.drawCardsIntoHand(1);
			}
		}
	}

	private int discardCards(Player activePlayer, Turn currentTurn, Supply supply) {
		int numberOfDiscardedCards = 0;

		while (activePlayer.getHandSize() > 0) {
			DiscardCardForCoinDecision decision = new DiscardCardForCoinDecision(activePlayer.getHand());
			activePlayer.decide(decision, this);
			if (decision.isCanceled()) {
				break;
			}
			activePlayer.discardCardsFromHand(decision.getAnswer(), currentTurn, supply);
			numberOfDiscardedCards++;
		}
		return numberOfDiscardedCards;
	}

}
