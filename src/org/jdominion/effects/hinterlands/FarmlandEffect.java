package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.OnXEffect;
import org.jdominion.effects.UpgradeEffect;
import org.jdominion.event.CardBought;

public class FarmlandEffect extends OnXEffect {

	public FarmlandEffect() {
		super(CardBought.class);
	}
	@Override
	protected void onX(Player gainingPlayer, Turn currentTurn, Supply supply) {
		UpgradeEffect upgrade = new UpgradeEffect(2);
		upgrade.execute(gainingPlayer, currentTurn, supply);
	}

}
