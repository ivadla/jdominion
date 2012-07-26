package org.jdominion.effects.hinterlands;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffect;
import org.jdominion.event.CardGained;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.IEventHandler;
import org.jdominion.event.EventManager.Duration;

public abstract class OnGainEffect extends CardEffect implements IEventHandler {

	public OnGainEffect() {
		super(null);
		EventManager.getInstance().addEventHandler(this, CardGained.class, Duration.FOREVER);
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
		assert event instanceof CardGained;
		CardGained cardGainedEvent = (CardGained) event;
		if (cardGainedEvent.getGainedCard().equals(getCard())) {
			onGain(cardGainedEvent.getAffectedPlayer(), cardGainedEvent.getCurrentTurn(), cardGainedEvent.getSupply());
		}
	}

	protected abstract void onGain(Player gainingPlayer, Turn currentTurn, Supply supply);

}
