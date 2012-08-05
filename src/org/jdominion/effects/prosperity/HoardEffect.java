package org.jdominion.effects.prosperity;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.cards.common.Gold;
import org.jdominion.effects.CardEffectInPlay;
import org.jdominion.event.CardBought;
import org.jdominion.event.Event;

public class HoardEffect extends CardEffectInPlay {

	public HoardEffect() {
		super(CardBought.class);
	}
	@Override
	protected void handleEventWhileInPlay(Event event, Player activePlayer, Turn currentTurn, Supply supply) {
		if (((CardBought) event).getBoughtCard().isOfType(Type.VICTORY)) {
			activePlayer.gainCard(Gold.class, supply, currentTurn);
		}
	}

}
