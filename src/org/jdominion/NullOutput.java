package org.jdominion;

import java.util.List;

public class NullOutput implements EventObserver {

	@Override
	public void buysCard(Player player, Card boughtCard) {
	}

	@Override
	public void discardsCards(Player player, List<Card> discardedCards) {
	}

	@Override
	public void drawsCards(Player player, int numberOfDrawnCards) {
	}

	@Override
	public void gainsCard(Player player, Card gainedCard) {
	}

	@Override
	public void playsCard(Player player, Card playedCard) {
	}

	@Override
	public void revealsCards(Player player, List<Card> revealedCards) {
	}

	@Override
	public void trashesCards(Player player, List<Card> trashedCards) {
	}
	
	@Override
	public void gameEnded(List<Player> winners, List<Player> players) {
	}

	@Override
	public void setCardsAside(Player player, List<Card> cardsToSetAside) {
	}

}
