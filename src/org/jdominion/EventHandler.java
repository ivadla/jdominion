package org.jdominion;

import java.util.ArrayList;
import java.util.List;

// TODO: this class should be replaced by the general event management
public class EventHandler implements EventObserver {

	private static EventHandler instance = null;
	private List<EventObserver> observers = new ArrayList<EventObserver>();

	public static EventHandler getInstance() {
		if (instance == null) {
			instance = new EventHandler();
		}
		return instance;
	}

	public void addObserver(EventObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void buysCard(Player player, Card boughtCard) {
		for (EventObserver observer : observers) {
			observer.buysCard(player, boughtCard);
		}
	}

	@Override
	public void discardsCards(Player player, List<Card> discardedCards) {
		for (EventObserver observer : observers) {
			observer.discardsCards(player, discardedCards);
		}
	}

	@Override
	public void drawsCards(Player player, int numberOfDrawnCards) {
		for (EventObserver observer : observers) {
			observer.drawsCards(player, numberOfDrawnCards);
		}
	}

	@Override
	public void gainsCard(Player player, Card gainedCard) {
		for (EventObserver observer : observers) {
			observer.gainsCard(player, gainedCard);
		}
	}

	@Override
	public void gameEnded(List<Player> winners, List<Player> players) {
		for (EventObserver observer : observers) {
			observer.gameEnded(winners, players);
		}
	}

	@Override
	public void playsCard(Player player, Card playedCard) {
		for (EventObserver observer : observers) {
			observer.playsCard(player, playedCard);
		}
	}

	@Override
	public void revealsCards(Player player, List<Card> revealedCards) {
		for (EventObserver observer : observers) {
			observer.revealsCards(player, revealedCards);
		}
	}

	@Override
	public void trashesCards(Player player, List<Card> trashedCards) {
		for (EventObserver observer : observers) {
			observer.trashesCards(player, trashedCards);
		}
	}

	@Override
	public void setCardsAside(Player player, List<Card> cardsToSetAside) {
		for (EventObserver observer : observers) {
			observer.setCardsAside(player, cardsToSetAside);
		}
	}

}
