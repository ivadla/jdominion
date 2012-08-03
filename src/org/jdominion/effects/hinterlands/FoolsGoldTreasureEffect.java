package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.hinterlands.FoolsGold;
import org.jdominion.effects.CardEffectTreasure;

public class FoolsGoldTreasureEffect extends CardEffectTreasure {

	public FoolsGoldTreasureEffect() {
		// for basic strategic evaluation we assume that the card provides one coin
		super(1);

	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		// playedCards already contains this FoolsGold at this stage
		if (currentTurn.getPlayedCards().countCard(FoolsGold.class) > 1) {
			currentTurn.addExtraMoney(4);
		} else {
			currentTurn.addExtraMoney(1);
		}
		return true;
	}
}
