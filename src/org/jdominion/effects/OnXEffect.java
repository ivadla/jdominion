package org.jdominion.effects;

import org.jdominion.CardList;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.event.CardEvent;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.EventManager.Duration;
import org.jdominion.event.IEventHandler;

/**
 * This class is for "When you gain/buy/trash this"-effects. You specify which one you want in the constructor
 * 
 */
public abstract class OnXEffect extends CardEffect implements IEventHandler {

	private Class<? extends CardEvent> eventToReactTo;

	public OnXEffect(Class<? extends CardEvent> eventToReactTo) {
		super(null);
		this.eventToReactTo = eventToReactTo;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		return true;
	}

	@Override
	public int getCoins() {
		return 0;
	}

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return 0;
	}

	@Override
	public void handleEvent(Event event) {
		CardEvent cardEvent = (CardEvent) event;
		if (cardEvent.getCard().hasEffect(getClass())) {
			OnXEffect onXEffect = (OnXEffect) cardEvent.getCard().getEffect(getClass());
			onXEffect.onX(cardEvent.getAffectedPlayer(), cardEvent.getCurrentTurn(), cardEvent.getSupply());
		}
	}

	protected abstract void onX(Player gainingPlayer, Turn currentTurn, Supply supply);

	@Override
	public void gameStarted(Game game) {
		EventManager.getInstance().addEventHandler(this, eventToReactTo, Duration.FOREVER);
	}
}
