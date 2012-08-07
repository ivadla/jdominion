package org.jdominion.effects.darkAges;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.effects.CardEffectAction;

public class PoorHouseEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		activePlayer.revealHand();
		int treasureCardsCount = activePlayer.getHand().countCardsOfType(Type.TREASURE);
		int newCoins = currentTurn.getExtraMoney() - treasureCardsCount;
		if (newCoins < 0) {
			newCoins = 0;
		}
		currentTurn.setExtraMoney(newCoins);
		return true;
	}

}
