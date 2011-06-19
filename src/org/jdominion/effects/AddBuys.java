package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class AddBuys extends CardEffectAction {

	private int numberOfBuysToAdd;

	public AddBuys(int numberOfBuysToAdd) {
		super();
		this.numberOfBuysToAdd = numberOfBuysToAdd;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		currentTurn.addBuys(numberOfBuysToAdd);
		return true;
	}

}
