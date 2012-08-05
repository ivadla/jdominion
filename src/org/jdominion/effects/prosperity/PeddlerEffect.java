package org.jdominion.effects.prosperity;

import org.jdominion.CardList;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.Turn.Phase;
import org.jdominion.cards.prosperity.Peddler;
import org.jdominion.effects.CardEffect;
import org.jdominion.event.CalculatingCardCost;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.IEventHandler;
import org.jdominion.event.EventManager.Duration;

public class PeddlerEffect extends CardEffect implements IEventHandler {

	private Game game;

	public PeddlerEffect() {
		super(null);
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
		EventManager.getInstance().addEventHandler(this, CalculatingCardCost.class, Duration.FOREVER);
	}

	@Override
	public void handleEvent(Event event) {
		CalculatingCardCost cardCostEvent = (CalculatingCardCost) event;
		if(cardCostEvent.getCard() instanceof Peddler) {
			if (game.getCurrentTurn().getCurrentPhase() == Phase.Buy) {
				cardCostEvent.decreaseCardCost(getNumberOfActionCardsInPlay(game) * 2);
			}
		}
	}

	private int getNumberOfActionCardsInPlay(Game game) {
		return game.getCurrentTurn().getActivePlayer().getCardsInPlay().countCardsOfType(Type.ACTION);
	}
}
