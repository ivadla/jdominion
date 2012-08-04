package org.jdominion.effects.seaside;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Gold;
import org.jdominion.cards.seaside.TreasureMap;
import org.jdominion.effects.TrashThisCard;
import org.jdominion.location.TopOfDeck;

public class TreasureMapEffect extends TrashThisCard {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {

		// trash the treasure map which has been played
		// this can go wrong(return false) if it has been trashed before(e.g. due to playing it twice with throne room)
		boolean gainGold = super.execute(activePlayer, currentTurn, supply);

		if (activePlayer.getHand().contains(TreasureMap.class)) {
			activePlayer.trashCard(activePlayer.getHand().getCardByClass(TreasureMap.class), currentTurn.getGame());
		} else {
			gainGold = false;
		}

		if (gainGold) {
			for (int i = 0; i < 4; i++) {
				activePlayer.gainCard(Gold.class, supply, new TopOfDeck(), currentTurn);
			}
		}

		return gainGold;
	}

}
