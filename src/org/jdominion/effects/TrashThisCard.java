package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class TrashThisCard extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (currentTurn.getGame().getTrash().contains(this.getCard())) {
			return false;
		} else {
			activePlayer.trashCard(getCard(), currentTurn.getGame());
			return true;
		}
	}

}
