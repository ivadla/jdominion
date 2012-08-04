package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.decisions.hinterlands.GainCopperDecision;
import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.location.Hand;

public class IllGottenGainsTreasureEffect extends CardEffectTreasure {

	public IllGottenGainsTreasureEffect() {
		super(1);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {

		super.execute(activePlayer, currentTurn, supply);

		GainCopperDecision decision = new GainCopperDecision();
		activePlayer.decide(decision, this);
		if (decision.getAnswer()) {
			activePlayer.gainCard(Copper.class, supply, new Hand(), currentTurn);
		}
		return true;
	}

}
