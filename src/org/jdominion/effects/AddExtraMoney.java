package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class AddExtraMoney extends CardEffectAction {

	private int coinsToAdd;

	public AddExtraMoney(int coinsToAdd) {
		super();
		this.coinsToAdd = coinsToAdd;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		currentTurn.addExtraMoney(coinsToAdd);
		return true;
	}

}
