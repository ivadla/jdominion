package org.jdominion.effects.darkAges;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Silver;
import org.jdominion.effects.OnXEffect;
import org.jdominion.event.CardTrashed;

public class FeodumOnTrashEffect extends OnXEffect {

	public FeodumOnTrashEffect() {
		super(CardTrashed.class);
	}

	@Override
	protected void onX(Player player, Turn currentTurn, Supply supply) {
		player.gainCard(Silver.class, supply, currentTurn);
		player.gainCard(Silver.class, supply, currentTurn);
		player.gainCard(Silver.class, supply, currentTurn);
	}

}
