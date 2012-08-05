package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.OnGetEffect;
import org.jdominion.effects.UpgradeEffect;
import org.jdominion.event.CardBought;

public class FarmlandEffect extends OnGetEffect {

	public FarmlandEffect() {
		super(CardBought.class);
	}
	@Override
	protected void onGet(Player gainingPlayer, Turn currentTurn, Supply supply) {
		UpgradeEffect upgrade = new UpgradeEffect(2);
		upgrade.execute(gainingPlayer, currentTurn, supply);
	}

}
