package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.GainCardWhichCostsUpToX;

public class BorderVillageOnGainEffect extends OnGainEffect {

	@Override
	protected void onGain(Player gainingPlayer, Turn currentTurn, Supply supply) {
		if (getCard().getCost() > 0) {
			GainCardWhichCostsUpToX gainEffect = new GainCardWhichCostsUpToX(getCard().getCost() - 1);
			gainEffect.execute(gainingPlayer, currentTurn, supply);
		}
	}

}
