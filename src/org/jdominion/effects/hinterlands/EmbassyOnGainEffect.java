package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Silver;
import org.jdominion.effects.OnGetEffect;
import org.jdominion.event.CardGained;

public class EmbassyOnGainEffect extends OnGetEffect {

	public EmbassyOnGainEffect() {
		super(CardGained.class);
	}

	@Override
	protected void onGet(Player gainingPlayer, Turn currentTurn, Supply supply) {
		for (Player player : currentTurn.getGame().getPlayers()) {
			if (!player.equals(gainingPlayer)) {
				player.gainCard(Silver.class, supply, currentTurn);
			}
		}
	}

}
