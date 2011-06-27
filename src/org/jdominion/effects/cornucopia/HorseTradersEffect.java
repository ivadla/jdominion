package org.jdominion.effects.cornucopia;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectReaction;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.IEventHandler;
import org.jdominion.event.StartOfTurn;
import org.jdominion.event.EventManager.Duration;

public class HorseTradersEffect extends CardEffectReaction implements IEventHandler {

	@Override
	public void handleReaction(Event event, Player reactingPlayer) {
		reactingPlayer.setCardAside(this.getCard());
		EventManager.getInstance().addEventHandler(this, StartOfTurn.class, Duration.ONCE, reactingPlayer);
	}

	@Override
	public void handleEvent(Event event, Player activePlayer, Turn currentTurn, Supply supply) {
		activePlayer.drawCardsIntoHand(1);
		activePlayer.addCardToHand(getCard());
	}

}
