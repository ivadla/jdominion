package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;

public class CacheOnGainEffect extends OnGainEffect {

	@Override
	protected void onGain(Player gainingPlayer, Turn currentTurn, Supply supply) {
		gainingPlayer.gainCard(Copper.class, supply, currentTurn);
		gainingPlayer.gainCard(Copper.class, supply, currentTurn);
	}

}
