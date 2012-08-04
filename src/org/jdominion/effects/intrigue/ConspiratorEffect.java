package org.jdominion.effects.intrigue;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.effects.CardEffectAction;

public class ConspiratorEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (currentTurn.getPlayedCards().countCardsOfType(Type.ACTION) >= 3) {
			activePlayer.drawCardsIntoHand(1);
			currentTurn.addActions(1);
		}
		return true;
	}

}
