package org.jdominion.event;

import java.util.List;

import org.jdominion.Player;

public class GameEnded extends Event {

	private List<Player> winners;
	private List<Player> allPlayers;

	public List<Player> getWinners() {
		return winners;
	}

	public List<Player> getAllPlayers() {
		return allPlayers;
	}

	public GameEnded(List<Player> winners, List<Player> allPlayers) {
		super(getGameEndMessage(winners, allPlayers), null); // TODO: replace null by something better
		this.winners = winners;
		this.allPlayers = allPlayers;
	}

	private static String getGameEndMessage(List<Player> winners, List<Player> allPlayers) {

		StringBuilder message = new StringBuilder();
		for (Player winner : winners) {
			message.append(winner.getName() + " and ");
		}
		message.delete(message.length() - 5, message.length());
		message.append(" won with " + winners.get(0).countVictoryPoints(null) + " points.\n");
		for (Player player : allPlayers) {
			if (!winners.contains(player)) {
				message.append(player.getName() + " has " + player.countVictoryPoints(null) + " points.\n");
			}
		}
		message.delete(message.length() - 1, message.length());
		return message.toString();
	}

}
