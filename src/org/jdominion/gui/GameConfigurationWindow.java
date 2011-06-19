package org.jdominion.gui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

public class GameConfigurationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private ActionListener controller;
	private JButton startButton = null;
	private JPanel playerSelectionTable = null;
	private JPanel contentPanel = null;
	private List<JComboBox> strategieSelectors = null;
	private List<JTextField> playerNameTextFields = null;
	private JComboBox playerNumberSelector = null;

	public GameConfigurationWindow(final List<String> playerNames, final List<String> strategies,
			final List<Integer> selectedStrategies, final int defaultNumberOfPlayers, final int maximumNumberOfPlayers,
			ActionListener controller) {
		this.controller = controller;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				initialize(playerNames, strategies, selectedStrategies, defaultNumberOfPlayers, maximumNumberOfPlayers);
			}
		});
	}

	public String getPlayerName(int player) {
		return playerNameTextFields.get(player).getText();
	}

	public int getSelectedStrategy(int player) {
		return strategieSelectors.get(player).getSelectedIndex();
	}

	public int getSelectedNumberOfPlayers() {
		return playerNumberSelector.getSelectedIndex() + 1;
	}

	private void initialize(List<String> playerNames, List<String> strategies, List<Integer> selectedStrategies,
			int defaultNumberOfPlayers, int maximumNumberOfPlayers) {
		initializePlayerSelectionTable(playerNames, strategies, selectedStrategies, defaultNumberOfPlayers,
				maximumNumberOfPlayers);
		setContentPane(getContentPanel());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel(new MigLayout());
			contentPanel.add(getStartButton(), new CC().cell(0,0).spanY(2));
			contentPanel.add(getPlayerSelectionTable(), new CC().cell(1,0));
			contentPanel.add(getSelectSupplyCardsButton(), new CC().cell(1,1));
			
		}
		return contentPanel;
	}

	private JButton getStartButton() {
		if (startButton == null) {
			startButton = new JButton("Start Game");
			startButton.addActionListener(controller);
		}
		return startButton;
	}
	
	private JButton getSelectSupplyCardsButton() {
		JButton button = new JButton("Select Cards for Supply");
		button.setActionCommand("selectSupplyCards");
		button.addActionListener(controller);
		return button;
	}

	private JPanel getPlayerSelectionTable() {
		return playerSelectionTable;
	}

	private void initializePlayerNumberSelector(int defaultNumberOfPlayers, int maximumNumberOfPlayers) {
		Integer[] playerNumbers = new Integer[maximumNumberOfPlayers];
		for (int i = 0; i < maximumNumberOfPlayers; i++) {
			playerNumbers[i] = i + 1;
		}

		playerNumberSelector = new JComboBox(playerNumbers);
		playerNumberSelector.setSelectedItem(defaultNumberOfPlayers);
		playerNumberSelector.addActionListener(controller);

	}

	private void initializePlayerSelectionTable(List<String> playerNames, List<String> strategies,
			List<Integer> selectedStrategies, int defaultNumberOfPlayers, int maximumNumberOfPlayers) {
		initializePlayerNameTextFields(playerNames);
		initializeStrategieSelectors(strategies, selectedStrategies, maximumNumberOfPlayers);
		playerSelectionTable = new JPanel(new MigLayout());

		playerSelectionTable.add(new JLabel("Number of Players:"));
		initializePlayerNumberSelector(defaultNumberOfPlayers, maximumNumberOfPlayers);
		playerSelectionTable.add(playerNumberSelector, new CC().wrap());

		for (int row = 0; row < maximumNumberOfPlayers; row++) {
			playerSelectionTable.add(playerNameTextFields.get(row));
			playerSelectionTable.add(strategieSelectors.get(row), new CC().wrap());
		}
		setEditableNumberOfPlayers(defaultNumberOfPlayers);
	}

	private void initializeStrategieSelectors(List<String> strategies, List<Integer> selectedStrategies,
			int maximumNumberOfPlayers) {
		strategieSelectors = new ArrayList<JComboBox>();
		for (int i = 0; i < maximumNumberOfPlayers; i++) {
			JComboBox strategySelector = new JComboBox(strategies.toArray());
			strategySelector.setSelectedIndex(selectedStrategies.get(i));
			strategieSelectors.add(strategySelector);
		}
	}

	private void initializePlayerNameTextFields(List<String> playerNames) {
		playerNameTextFields = new ArrayList<JTextField>();
		for (String name : playerNames) {
			playerNameTextFields.add(new JTextField(name, 10));
		}
	}

	public void setEditableNumberOfPlayers(int editablePlayers) {
		for (int playerNumber = 0; playerNumber < playerNameTextFields.size(); playerNumber++) {
			if (playerNumber < editablePlayers) {
				setPlayerEditable(playerNumber, true);
			} else {
				setPlayerEditable(playerNumber, false);
			}
		}
	}

	private void setPlayerEditable(int playerNumber, boolean editable) {
		playerNameTextFields.get(playerNumber).setEnabled(editable);
		strategieSelectors.get(playerNumber).setEnabled(editable);
	}
}
