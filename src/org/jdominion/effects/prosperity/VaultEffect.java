package org.jdominion.effects.prosperity;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.prosperity.DiscardTwoCardsToDrawOne;
import org.jdominion.effects.CardEffectAction;

public class VaultEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
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


}
