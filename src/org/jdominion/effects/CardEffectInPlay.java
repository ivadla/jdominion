package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.IEventHandler;
import org.jdominion.event.EventManager.Duration;

public abstract class CardEffectInPlay extends CardEffect implements IEventHandler {

	private Game game;
	private Class<? extends Event> eventType;

	public CardEffectInPlay(Class<? extends Event> eventType) {
		super(null);
		this.eventType = eventType;
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
	public void gameStarted(Game game) {
		this.game = game;
		EventManager.getInstance().addEventHandler(this, eventType, Duration.FOREVER);
	}

	@Override
	public void handleEvent(Event event) {
		// it's possible that some events happen before the game even started, but the card can't possibly be in play at
		// this point. We ignore the event, because game.getCurrentTurn() would return null
		// and cause a NullPointerException
		if (game.gameIsRunning()) {
			for (Card card : game.getCurrentTurn().getCardsInPlay().getCardsByEffectClass(getClass())) {
				CardEffectInPlay inPlayEffect = (CardEffectInPlay) card.getEffect(getClass());
				inPlayEffect.handleEventWhileInPlay(event, game.getCurrentTurn().getActivePlayer(), game.getCurrentTurn(), game.getSupply());
			}
		}
	}

	protected abstract void handleEventWhileInPlay(Event event, Player activePlayer, Turn currentTurn, Supply supply);
}
