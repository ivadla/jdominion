package org.jdominion.effects.base;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.effects.CardEffectAction;

public class TrashCopper extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (activePlayer.getHand().contains(Copper.class)) {
			Card cardToTrash = activePlayer.getHand().getCardByClass(Copper.class);
			activePlayer.trashCard(cardToTrash, currentTurn.getGame());
			return true;
		}
		return false;
	}

}
