package org.jdominion.effects.darkAges;

import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;
import org.jdominion.decisions.darkAges.ChooseActionCardFromHandToTrash;
import org.jdominion.effects.base.RemodelEffect;

public class RemodelActionCard extends RemodelEffect {

	public RemodelActionCard(int addedValue) {
		super(addedValue);
	}

	@Override
	protected ChooseCardsFromHandToTrash createDecision(Hand hand) {
		return new ChooseActionCardFromHandToTrash(new Hand(hand.getCardsOfType(Type.ACTION)));
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (activePlayer.getHand().countCardsOfType(Type.ACTION) == 0) {
			return false;
		}
		return super.execute(activePlayer, currentTurn, supply);
	}

	@Override
	public String getText() {
		return "trash an Action card from your hand and gain a card costing up to 3 more than it.";
	}

}
