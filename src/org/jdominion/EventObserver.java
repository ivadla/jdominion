package org.jdominion;

import java.util.List;

public interface EventObserver {
	public void playsCard(Player player, Card playedCard);

	public void discardsCards(Player player, List<Card> discardedCards);

	public void trashesCards(Player player, List<Card> trashedCards);

	public void drawsCards(Player player, int numberOfDrawnCards);

	public void buysCard(Player player, Card boughtCard);

	public void gainsCard(Player player, Card gainedCard);

	public void revealsCards(Player player, List<Card> revealedCards);

	public void setCardsAside(Player player, List<Card> cardsToSetAside);

	public void gameEnded(List<Player> winners, List<Player> players);

}
