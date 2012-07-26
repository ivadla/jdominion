package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.cards.hinterlands.Crossroads;
import org.jdominion.effects.CardEffectAction;

public class CrossroadsEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		activePlayer.revealHand();
		activePlayer.drawCardsIntoHand(activePlayer.getHand().countCardsOfType(Type.VICTORY));

		// playedCards already contains this crossroads at this stage
		if (currentTurn.getPlayedCards().countCard(Crossroads.class) == 1) {
			currentTurn.addActions(3);
		}
		return true;
	}

}