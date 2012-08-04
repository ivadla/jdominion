package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.event.CalculatingCardCost;
import org.jdominion.event.Event;

public class ReduceCostsOfCards extends CardEffectInPlay {

	private int costDecrement;

	public ReduceCostsOfCards(int costDecrement) {
		super(CalculatingCardCost.class);
		this.costDecrement = costDecrement;
	}

	@Override
	protected void handleEventWhileInPlay(Event event, Player activePlayer, Turn currentTurn, Supply supply) {
		((CalculatingCardCost) event).decreaseCardCost(costDecrement);
	}

}
