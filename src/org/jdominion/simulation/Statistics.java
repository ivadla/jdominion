package org.jdominion.simulation;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdominion.Game;
import org.jdominion.Player;

public class Statistics {
	private int[][] statistics;
	private int gameCounter = 0;
	private int turnCounter = 0;
	private IExtraStatistics statisticSupplier = null;
	private int[] extraStatistics;
	private List<PlayerInformationContainer> playerInformation;

	public Statistics(List<PlayerInformationContainer> playerInformation) {
		this.playerInformation = playerInformation;
		statistics = new int[playerInformation.size()][playerInformation.size()];
	}

	public Statistics(List<PlayerInformationContainer> playerInformation, IExtraStatistics statisticSupplier) {
		this(playerInformation);
		this.statisticSupplier = statisticSupplier;
		this.extraStatistics = new int[playerInformation.size()];
	}

	public void updateStatistics(Game game, List<Player> winners) {
		gameCounter++;
		turnCounter += game.getTurnCounter();

		for (Player winner : winners) {
			increasePlayerWinCounter(winner, winners.size());
		}

		for (Player player : game.getPlayers()) {
			updateExtraStatistics(player);
		}
	}

	private void updateExtraStatistics(Player player) {
		if (statisticSupplier == null) {
			return;
		}

		extraStatistics[player.getId()] += statisticSupplier.getValue(player);
	}

	public String getStatistics() {
		String statisticsText = gameCounter + " Games simulated. Average number of turns: "
				+ ((double) turnCounter / (double) gameCounter) + ".\n";
		for (PlayerInformationContainer playerInfo : playerInformation) {
			statisticsText += playerInfo.getName() + " with parameters "
					+ StringUtils.join(playerInfo.getParameters(), ",") + " won "
					+ formatValueAndPercentage(statistics[playerInfo.getId()][0]);
			for (int i = 1; i < playerInformation.size(); i++) {
				statisticsText += "; tied with " + (i + 1) + " players "
						+ formatValueAndPercentage(statistics[playerInfo.getId()][i]);
				;
			}
			statisticsText += getExtraStatistics(playerInfo.getId());
			statisticsText += "\n";

		}
		return statisticsText;
	}

	public String getCSVStatistics(String description) {
		String title = "gameDescription;totalGames;TotalTurns;";
		String statisticsText = description + ";" + gameCounter + ";" + turnCounter + ";";
		for (PlayerInformationContainer playerInfo : playerInformation) {
			title += "playerName;parameters;numOfGamesWon;";
			statisticsText += playerInfo.getName() + ";" + StringUtils.join(playerInfo.getParameters(), ",") + ";"
					+ statistics[playerInfo.getId()][0] + ";";
			for (int i = 1; i < playerInformation.size(); i++) {
				title += "tiedWith" + (i + 1) + "players;";
				statisticsText += statistics[playerInfo.getId()][i] + ";";
			}
			statisticsText += getExtraStatistics(playerInfo.getId());
		}
		return statisticsText;
	}

	private String getExtraStatistics(int playerId) {
		if (statisticSupplier == null) {
			return "";
		}
		return "; " + statisticSupplier.getName() + ": " + (double) extraStatistics[playerId] / (double) gameCounter;
	}

	private void increasePlayerWinCounter(Player winningPlayer, int numberOfWinners) {
		statistics[winningPlayer.getId()][numberOfWinners - 1]++;
	}

	private String formatValueAndPercentage(int number) {
		return number + " times (" + getPercentage(number) + ")";
	}

	private String getPercentage(int number) {
		return 100.0 * (double) number / (double) gameCounter + "%";
	}

}
