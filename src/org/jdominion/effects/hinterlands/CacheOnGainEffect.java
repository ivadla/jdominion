package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.effects.OnXEffect;
import org.jdominion.event.CardGained;

public class CacheOnGainEffect extends OnXEffect {

	public CacheOnGainEffect() {
		super(CardGained.class);
	}

	@Override
	protected void onX(Player gainingPlayer, Turn currentTurn, Supply supply) {
		gainingPlayer.gainCard(Copper.class, supply, currentTurn);
		gainingPlayer.gainCard(Copper.class, supply, currentTurn);
	}

}
