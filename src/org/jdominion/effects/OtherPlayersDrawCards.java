package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class OtherPlayersDrawCards extends CardEffectAction {

	private int numberOfCardsToDraw;

	public OtherPlayersDrawCards(int numberOfCardsToDraw) {
		super();
		this.numberOfCardsToDraw = numberOfCardsToDraw;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		for (Player player : currentTurn.getOtherPlayers()) {
			player.drawCardsIntoHand(this.numberOfCardsToDraw);
		}
		return true;
	}
}
