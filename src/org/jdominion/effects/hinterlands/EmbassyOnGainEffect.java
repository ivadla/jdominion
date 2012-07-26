package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Silver;

public class EmbassyOnGainEffect extends OnGainEffect {

	@Override
	protected void onGain(Player gainingPlayer, Turn currentTurn, Supply supply) {
		for (Player player : currentTurn.getGame().getPlayers()) {
			if (!player.equals(gainingPlayer)) {
				player.gainCard(Silver.class, supply, currentTurn);
			}
		}
	}


}
