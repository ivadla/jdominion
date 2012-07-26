package org.jdominion.effects.intrigue;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.effects.CardEffectAction;
import org.jdominion.event.CalculateValue;
import org.jdominion.event.EndOfTurn;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.IEventHandler;
import org.jdominion.event.EventManager.Duration;

public class CoppersmithEffect extends CardEffectAction implements IEventHandler {

	/**
	 * currentCopperValueIncrement is increased by 1 every time the coppersmith is played and reduced to 0 at the end of
	 * the turn we have to do this, because a throne room can play the same card twice, but we can't register twice for
	 * the event
	 * 
	 * TODO: maybe it's better to allow multiple registrations for the same event
	 */
	private int currentCopperValueIncrement = 0;
	public boolean registeredAsEventHandler = false;

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {

		currentCopperValueIncrement += 1;

		registerEventHandlers();

		return true;
	}

	private void registerEventHandlers() {
		if (!registeredAsEventHandler) {
			EventManager.getInstance().addEventHandler(this, CalculateValue.class, Duration.FOREVER);
			EventManager.getInstance().addEventHandler(this, EndOfTurn.class, Duration.FOREVER);
			registeredAsEventHandler = true;
		}
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof CalculateValue) {
			CalculateValue calculateValueEvent = ((CalculateValue) event);
			if (calculateValueEvent.getCard() instanceof Copper) {
				calculateValueEvent.increaseValue(currentCopperValueIncrement);
			}
		} else if (event instanceof EndOfTurn) {
			unregisterEventHandlers();
			currentCopperValueIncrement = 0;
		} else {
			assert false : "Can't handle event type " + event.getClass();
		}
	}

	private void unregisterEventHandlers() {
		EventManager.getInstance().removeEventHandler(this, CalculateValue.class);
		EventManager.getInstance().removeEventHandler(this, EndOfTurn.class);
		registeredAsEventHandler = false;
	}
}
