package org.jdominion.effects.base;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseActionCardToPlay;
import org.jdominion.effects.CardEffectAction;

public class ThroneRoomEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (!activePlayer.hasActionCardInHand()) {
			return false;
		}

		ChooseActionCardToPlay decision = new ChooseActionCardToPlay(activePlayer.getHand(), false);
		activePlayer.decide(decision, this, activePlayer.getHand(), currentTurn, supply);
		assert decision.getAnswer().size() == 1;
		Card choosenCard = decision.getAnswer().get(0);
		assert choosenCard.isOfType(Type.ACTION);
		currentTurn.getPlayedCards().add(choosenCard);
		activePlayer.playCard(choosenCard, currentTurn, supply);
		activePlayer.playCard(choosenCard, currentTurn, supply);

		return true;
	}

}
