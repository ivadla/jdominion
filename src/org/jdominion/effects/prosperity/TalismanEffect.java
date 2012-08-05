package org.jdominion.effects.prosperity;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.effects.CardEffectInPlay;
import org.jdominion.event.CardBought;
import org.jdominion.event.Event;

public class TalismanEffect extends CardEffectInPlay {

	public TalismanEffect() {
		super(CardBought.class);
	}

	@Override
	protected void handleEventWhileInPlay(Event event, Player activePlayer, Turn currentTurn, Supply supply) {
		Card boughtCard = ((CardBought) event).getBoughtCard();
		if (boughtCard.isOfType(Type.VICTORY)) {
			return;
		}

		if (boughtCard.getCost() <= 4) {
			activePlayer.gainCard(boughtCard.getClass(), supply, currentTurn);
		}
	}

}
