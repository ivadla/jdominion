package org.jdominion.effects.darkAges;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.effects.CardEffectAction;
import org.jdominion.location.Hand;

public class BeggarGainCopperEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		activePlayer.gainCard(Copper.class, supply, new Hand(), currentTurn);
		activePlayer.gainCard(Copper.class, supply, new Hand(), currentTurn);
		activePlayer.gainCard(Copper.class, supply, new Hand(), currentTurn);
		return true;
	}

}
