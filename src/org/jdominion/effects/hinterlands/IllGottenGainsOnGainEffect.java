package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Curse;
import org.jdominion.effects.OnXEffect;
import org.jdominion.event.CardGained;

public class IllGottenGainsOnGainEffect extends OnXEffect {

	public IllGottenGainsOnGainEffect() {
		super(CardGained.class);
	}

	@Override
	protected void onX(Player gainingPlayer, Turn currentTurn, Supply supply) {
		for (Player player : currentTurn.getOtherPlayers()) {
			player.gainCard(Curse.class, supply, currentTurn);
		}
	}

}
