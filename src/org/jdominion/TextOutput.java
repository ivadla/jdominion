package org.jdominion;

import java.io.Serializable;
import java.util.List;

public class TextOutput implements EventObserver, Serializable {

	boolean gameEndMessageDisplayed = false;

	protected void printMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void buysCard(Player player, Card boughtCard) {
		printMessage(player.getName() + " buys " + boughtCard.getName() + ".");
	}

	@Override
	public void discardsCards(Player player, List<Card> discardedCards) {
		printMessage(player.getName() + " discards " + convertCardListToString(discardedCards) + ".");
	}

	@Override
	public void drawsCards(Player player, int numberOfDrawnCards) {
		printMessage(player.getName() + " draws " + numberOfDrawnCards + " cards.");
	}

	@Override
	public void gainsCard(Player player, Card gainedCard) {
		printMessage(player.getName() + " gains " + gainedCard.getName() + ".");
	}

	@Override
	public void playsCard(Player player, Card playedCard) {
		printMessage(player.getName() + " plays " + playedCard.getName() + ".");
	}

	@Override
	public void trashesCards(Player player, List<Card> trashedCards) {
		printMessage(player.getName() + " trashes " + convertCardListToString(trashedCards) + ".");
	}

	@Override
	public void revealsCards(Player player, List<Card> revealedCards) {
		String message = player.getName() + " reveals " + convertCardListToString(revealedCards) + ".";
		printMessage(message);
	}

	@Override
	public void setCardsAside(Player player, List<Card> cardsToSetAside) {
		printMessage(player.getName() + " sets " + convertCardListToString(cardsToSetAside) + " aside.");
	}

	@Override
	public void gameEnded(List<Player> winners, List<Player> players) {

		if (gameEndMessageDisplayed) {
			return;
		}

		StringBuilder message = new StringBuilder();
		for (Player winner : winners) {
			message.append(winner.getName() + " and ");
		}
		message.delete(message.length() - 5, message.length());
		message.append(" won with " + winners.get(0).countVictoryPoints(null) + " points.\n");
		for (Player player : players) {
			if (!winners.contains(player)) {
				message.append(player.getName() + " has " + player.countVictoryPoints(null) + " points.\n");
			}
		}
		message.delete(message.length() - 1, message.length());
		printMessage(message.toString());
		gameEndMessageDisplayed = true;
	}
	
	private String convertCardListToString(List<Card> cards) {
		String message = "";
		if (cards.size() == 0) {
			message += "no cards";
		} else {
			for (int i = 0; i < cards.size() - 1; i++) {
				message += cards.get(i).getName() + ", ";
			}
			message += cards.get(cards.size() - 1).getName();
		}
		return message;
	}

}
