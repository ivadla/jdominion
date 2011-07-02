package org.jdominion.simulation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jdominion.Card;
import org.jdominion.CardFactory;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.aiStrategies.IStrategy;

public class Simulator {
	private List<PlayerInformationContainer> players;
	private int numberOfGamesToSimulate;

	public Simulator(int numberOfGamesToSimulate) {
		players = new ArrayList<PlayerInformationContainer>();
		this.numberOfGamesToSimulate = numberOfGamesToSimulate;
	}

	public void addPlayer(Class<? extends IStrategy> strategy) {
		addPlayer(strategy, new ArrayList<SimulationParameter>());
	}

	public void addPlayer(Class<? extends IStrategy> strategy, List<SimulationParameter> parameters) {
		PlayerInformationContainer player = new PlayerInformationContainer(players.size(), strategy, parameters);
		players.add(player);
	}

	public void run() {
		run(null);
	}

	public void run(IExtraStatistics statisticSupplier) {
		Deque<SimulationParameter> allParameters = new ArrayDeque<SimulationParameter>();
		for (PlayerInformationContainer playerInformation : players) {
			allParameters.addAll(playerInformation.getParameters());
		}

		runRecursivelyWithAllParameterCombinations(statisticSupplier, allParameters);

	}

	private void runRecursivelyWithAllParameterCombinations(IExtraStatistics statisticSupplier,
			Deque<SimulationParameter> originalParameters) {
		Deque<SimulationParameter> parameters = new ArrayDeque<SimulationParameter>(originalParameters);
		
		// only run the simulation if we are at the last parameter
		if(originalParameters.isEmpty()) {
			simulate(statisticSupplier);
			return;
		}
		
		SimulationParameter currentParameter = parameters.removeFirst();

		// go through all possibilities for this parameter once
		assert currentParameter.getCurrentValue() == currentParameter.getMin();
		do {

			// recurse to iterate over the other parameters
			runRecursivelyWithAllParameterCombinations(statisticSupplier, parameters);

			currentParameter.increaseValue();
			// the parameter loops back to minValue a the end
		} while (currentParameter.getCurrentValue() != currentParameter.getMin());
	}

	private IStrategy initializeStrategy(Class<? extends IStrategy> strategy, List<SimulationParameter> parameters) {

		try {
			if (parameters.isEmpty()) {
				return strategy.newInstance();
			} else {
				Class<?>[] parameterTypes = new Class<?>[parameters.size()];
				Object[] parameterArray = new Integer[parameters.size()];
				for (int i = 0; i < parameters.size(); i++) {
					parameterTypes[i] = int.class;
					parameterArray[i] = parameters.get(i).getCurrentValue();
				}
				// TODO: put information about parameters in player names
				// System.out.println("Creating Strategy " + strategy.getName()
				// + " with parameters " + parameterList);
				return strategy.getConstructor(parameterTypes).newInstance(parameterArray);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error while creating Strategy " + strategy.getName() + " with parameters "
					+ StringUtils.join(parameters, ","), e);
		}
	}

	private void simulate(IExtraStatistics statisticSupplier) {
		Statistics statistics = new Statistics(players, statisticSupplier);

		for (int gameCounter = 0; gameCounter < numberOfGamesToSimulate; gameCounter++) {
			List<Player> players = createPlayers();
			Game game = createGame(players);

			game.runGame();

			statistics.updateStatistics(game, game.getWinners());
		}
		System.out.print(statistics.getStatistics());
	}

	private Game createGame(List<Player> players) {

		return new Game(players, CardFactory.createSupply(players.size(), new ArrayList<Class<? extends Card>>(
				getNeededKingdomCards(players))));
	}

	private Set<Class<? extends Card>> getNeededKingdomCards(List<Player> players) {
		Set<Class<? extends Card>> kingdomCards = new HashSet<Class<? extends Card>>();
		for (Player player : players) {
			kingdomCards.addAll(player.getNeededCards());
		}
		return kingdomCards;
	}

	private List<Player> createPlayers() {
		List<Player> realPlayers = new ArrayList<Player>();

		for (PlayerInformationContainer playerInfo : players) {
			IStrategy strategy = initializeStrategy(playerInfo.getStrategy(), playerInfo.getParameters());
			playerInfo.setName(strategy.getName());
			realPlayers.add(new Player(playerInfo.getId(), strategy.getName(), CardFactory.createInitialDeck(),
					strategy));
		}

		Collections.shuffle(realPlayers);
		return realPlayers;
	}

}
