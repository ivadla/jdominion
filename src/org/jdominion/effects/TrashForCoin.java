package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;

public class TrashForCoin extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (activePlayer.getHandSize() == 0) {
			return false;
		}
		ChooseCardsFromHandToTrash trashDecision = new ChooseCardsFromHandToTrash(false, 1, 1, activePlayer.getHand());
		activePlayer.decide(trashDecision, this, activePlayer.getHand(), currentTurn, supply);

		assert trashDecision.getAnswer().size() == 1;
		Card cardToTrash = trashDecision.getAnswer().get(0);
		assert cardToTrash != null;
		
		if (cardToTrash.getCost() > 0) {
			currentTurn.addExtraMoney(cardToTrash.getCost());
		}
		activePlayer.trashCard(cardToTrash, currentTurn.getGame());

		return true;
	}

}
