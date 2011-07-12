package org.jdominion.effects;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

/**
 * This effect does nothing. It is just a placeholder for cases where a decision is needed which does not come from a
 * card. E.g. buying in the buy phase
 */
public class NullEffect extends CardEffect {

	public NullEffect() {
		super(null);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		return false;
	}

	@Override
	public int getCoins() {
		return 0;
	}

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return 0;
	}
}
