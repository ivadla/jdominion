package org.jdominion.effects.darkAges;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.OnXEffect;
import org.jdominion.event.CardTrashed;

public class OnTrashDrawXCardsEffect extends OnXEffect {

	private int numberOfCardsToDraw;

	public OnTrashDrawXCardsEffect(int numberOfCardsToDraw) {
		super(CardTrashed.class);
		this.numberOfCardsToDraw = numberOfCardsToDraw;
	}

	@Override
	protected void onX(Player gainingPlayer, Turn currentTurn, Supply supply) {
		gainingPlayer.drawCardsIntoHand(this.numberOfCardsToDraw);
	}

}
