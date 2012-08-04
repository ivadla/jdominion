package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Curse;

public class IllGottenGainsOnGainEffect extends OnGainEffect {

	@Override
	protected void onGain(Player gainingPlayer, Turn currentTurn, Supply supply) {
		for (Player player : currentTurn.getOtherPlayers()) {
			player.gainCard(Curse.class, supply, currentTurn);
		}
	}

}
