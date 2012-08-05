package org.jdominion.effects;

import org.jdominion.CardList;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.event.CardReceived;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.IEventHandler;
import org.jdominion.event.EventManager.Duration;

/**
 * This class is for "When you gain/buy this"-effects. You specify which one you want in the constructor
 * 
 */
public abstract class OnGetEffect extends CardEffect implements IEventHandler {

	private Class<? extends CardReceived> getEvent;

	public OnGetEffect(Class<? extends CardReceived> getEvent) {
		super(null);
		this.getEvent = getEvent;
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
		CardReceived cardReceiveEvent = (CardReceived) event;
		if (cardReceiveEvent.getReceivedCard().hasEffect(getClass())) {
			OnGetEffect onGetEffect = (OnGetEffect) cardReceiveEvent.getReceivedCard().getEffect(getClass());
			onGetEffect.onGet(cardReceiveEvent.getAffectedPlayer(), cardReceiveEvent.getCurrentTurn(), cardReceiveEvent.getSupply());
		}
	}

	protected abstract void onGet(Player gainingPlayer, Turn currentTurn, Supply supply);

	@Override
	public void gameStarted(Game game) {
		EventManager.getInstance().addEventHandler(this, getEvent, Duration.FOREVER);
	}
}
