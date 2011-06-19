package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.event.Attack;
import org.jdominion.event.CardPlayFinished;
import org.jdominion.event.CardPlayed;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.IEventHandler;
import org.jdominion.event.EventManager.Duration;

public class MoatEffect extends CardEffectReaction implements IEventHandler {

	@Override
	public void handleReaction(Event event, Player reactingPlayer) {
		if (event instanceof CardPlayed) {
			CardPlayed cardPlayedEvent = (CardPlayed) event;
			if (cardPlayedEvent.getPlayedCard().isOfType(Type.ATTACK)) {
				EventManager.getInstance().addEventHandler(this, Attack.class, Duration.END_OF_TURN);
				EventManager.getInstance().addEventHandler(this, CardPlayFinished.class, Duration.END_OF_TURN);
			}
		}
	}

	@Override
	public void handleEvent(Event event, Player activePlayer, Turn currentTurn, Supply supply) {
		if (event instanceof Attack) {
			Attack attackEvent = (Attack) event;
			if(attackEvent.getAttackedPlayer() == this.getOwner()) {
				attackEvent.setCanceled(true);
			}
		} else if (event instanceof CardPlayFinished) {
			EventManager.getInstance().removeEventHandler(this, Attack.class);
		}
	}

}
