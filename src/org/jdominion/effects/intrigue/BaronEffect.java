package org.jdominion.effects.intrigue;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Estate;
import org.jdominion.decisions.intrigue.DiscardEstateFor4CoinsDecision;
import org.jdominion.effects.CardEffectAction;

public class BaronEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {

		if (activePlayer.getHand().contains(Estate.class) && playerWantsToDiscardEstate(activePlayer)) {
			activePlayer.discardCardsFromHand(new CardList(activePlayer.getHand().getCardByClass(Estate.class)), currentTurn, supply);
			currentTurn.addExtraMoney(4);
		} else {
			activePlayer.gainCard(Estate.class, supply, currentTurn);
		}
		return true;
	}

	private boolean playerWantsToDiscardEstate(Player player) {
		DiscardEstateFor4CoinsDecision decision = new DiscardEstateFor4CoinsDecision();
		player.decide(decision, this);
		return decision.getAnswer();
	}

}
