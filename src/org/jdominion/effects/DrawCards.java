package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class DrawCards extends CardEffectAction {

	private int numberOfCardsToDraw;

	public DrawCards(int numberOfCardsToDraw) {
		super();
		this.numberOfCardsToDraw = numberOfCardsToDraw;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		activePlayer.drawCardsIntoHand(this.numberOfCardsToDraw);
		return true;
	}

}
