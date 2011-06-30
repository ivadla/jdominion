package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class AddActions extends CardEffectAction {

	private int numberOfActionsToAdd;

	public AddActions(int numberOfActionsToAdd) {
		super();
		this.numberOfActionsToAdd = numberOfActionsToAdd;
	}

	@Override
	public String getText() {
		return "+ " + numberOfActionsToAdd + " Action" + (numberOfActionsToAdd > 1 ? "s": "");
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		currentTurn.addActions(numberOfActionsToAdd);
		return true;
	}

	@Override
	public int getAddedActions() {
		return numberOfActionsToAdd;
	}

}
