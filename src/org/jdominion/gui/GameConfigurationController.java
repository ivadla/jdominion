package org.jdominion.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.jdominion.ClassFinder;
import org.jdominion.Game;
import org.jdominion.aiStrategies.IStrategy;
import org.jdominion.supplyCreators.SupplyCreator;

public class GameConfigurationController implements ActionListener {

	// make sure that you update Config.getPlayerNames() and
	// Config.getPlayerStrategies() if you change this number
	private final int MAXIMUM_PLAYERS = 4;
	private GameConfigurationWindow view;
	private List<String> playerNames;
	private List<Class<? extends IStrategy>> strategies;
	private List<Class<? extends IStrategy>> availableStrategies;
	private SupplyCreator supplyCreator;

	public GameConfigurationController() {

		this.supplyCreator = new SupplyCreator();

		this.availableStrategies = ClassFinder.findStrategies();

		this.strategies = Config.getPlayerStrategies();
		assert strategies.size() == MAXIMUM_PLAYERS;
		List<Integer> selectedStrategies = new ArrayList<Integer>();
		for (int i = 0; i < MAXIMUM_PLAYERS; i++) {
			selectedStrategies.add(availableStrategies.indexOf(strategies.get(i)));
		}

		this.playerNames = Config.getPlayerNames();
		assert playerNames.size() == MAXIMUM_PLAYERS;

		List<String> strategyNames = new ArrayList<String>();
		for (Class<? extends IStrategy> strategy : availableStrategies) {
			strategyNames.add(strategy.getSimpleName());
		}

		this.view = new GameConfigurationWindow(playerNames, strategyNames, selectedStrategies, Config
				.getNumberOfPlayers(), MAXIMUM_PLAYERS, this);
	}

	private Game createGame() {
		updatePlayerNamesAndStrategies();
		Config.setPlayerNames(playerNames);
		Config.setPlayerStrategies(strategies);
		Config.setNumberOfPlayers(view.getSelectedNumberOfPlayers());
		return new Game(playerNames, strategies, supplyCreator.getSelectedCards());
	}

	private void updatePlayerNamesAndStrategies() {
		playerNames = new ArrayList<String>();
		strategies = new ArrayList<Class<? extends IStrategy>>();
		for (int i = 0; i < view.getSelectedNumberOfPlayers(); i++) {
			playerNames.add(view.getPlayerName(i));
			strategies.add(availableStrategies.get(view.getSelectedStrategy(i)));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start Game")) {
			startGame();
		} else if (e.getActionCommand().equals("comboBoxChanged")) {
			selectedPlayersChanged();
		} else if (e.getActionCommand().equals("selectSupplyCards")) {
			new SelectCardsController(view, supplyCreator);
		}
	}

	private void startGame() {
		final Thread gameThread = new GameThread(createGame());
		gameThread.start();
		view.setVisible(false);

		// TODO: make Configuration Window visible again when the MainWindow has
		// been closed
		// new Timer(100, new ActionListener() {
		//			
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// if(gameThread.isAlive()) {
		// setVisible(true);
		// }
		//				
		// }
		// });
	}

	private void selectedPlayersChanged() {
		view.setEditableNumberOfPlayers(view.getSelectedNumberOfPlayers());
	}

}
