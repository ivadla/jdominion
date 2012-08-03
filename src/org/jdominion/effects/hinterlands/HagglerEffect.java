package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectInPlay;
import org.jdominion.event.CardBought;
import org.jdominion.event.Event;

public class HagglerEffect extends CardEffectInPlay {

	public HagglerEffect() {
		super(CardBought.class);
	}

	@Override
	protected void handleEventWhileInPlay(Event event, Player activePlayer, Turn currentTurn, Supply supply) {
		CardBought buyEvent = (CardBought) event;
		if (buyEvent.getBoughtCard().getCost() > 0) {
			GainNonVictoryCardWhichCostsUpToX gainEffect = new GainNonVictoryCardWhichCostsUpToX(buyEvent.getBoughtCard().getCost() - 1);
			gainEffect.execute(event.getAffectedPlayer(), currentTurn, supply);
		}
	}

}
