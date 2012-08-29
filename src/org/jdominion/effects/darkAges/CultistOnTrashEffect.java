package org.jdominion.effects.darkAges;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.OnXEffect;
import org.jdominion.event.CardTrashed;

public class CultistOnTrashEffect extends OnXEffect {

	public CultistOnTrashEffect() {
		super(CardTrashed.class);
	}

	@Override
	protected void onX(Player gainingPlayer, Turn currentTurn, Supply supply) {
		gainingPlayer.drawCardsIntoHand(3);
	}

}
