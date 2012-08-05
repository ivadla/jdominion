package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.effects.OnGetEffect;
import org.jdominion.event.CardGained;

public class CacheOnGainEffect extends OnGetEffect {

	public CacheOnGainEffect() {
		super(CardGained.class);
	}

	@Override
	protected void onGet(Player gainingPlayer, Turn currentTurn, Supply supply) {
		gainingPlayer.gainCard(Copper.class, supply, currentTurn);
		gainingPlayer.gainCard(Copper.class, supply, currentTurn);
	}

}
