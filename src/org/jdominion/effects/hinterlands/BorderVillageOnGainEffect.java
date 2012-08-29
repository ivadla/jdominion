package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.GainCardWhichCostsUpToX;
import org.jdominion.effects.OnXEffect;
import org.jdominion.event.CardGained;

public class BorderVillageOnGainEffect extends OnXEffect {

	public BorderVillageOnGainEffect() {
		super(CardGained.class);
	}

	@Override
	protected void onX(Player gainingPlayer, Turn currentTurn, Supply supply) {
		if (getCard().getCost() > 0) {
			GainCardWhichCostsUpToX gainEffect = new GainCardWhichCostsUpToX(getCard().getCost() - 1);
			gainEffect.execute(gainingPlayer, currentTurn, supply);
		}
	}

}
