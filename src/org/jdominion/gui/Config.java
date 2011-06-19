package org.jdominion.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.jdominion.Card;
import org.jdominion.ClassFinder;
import org.jdominion.aiStrategies.ChapelLab;
import org.jdominion.aiStrategies.IStrategy;

public class Config {

	private static Preferences getPrefs() {
		return Preferences.userNodeForPackage(Config.class);
	}

	private static List<String> getList(String key, String[] defaults) {
		List<String> stringList = new ArrayList<String>();
		Preferences subNode = getPrefs().node(key);
		for (Integer i = 0; i < defaults.length; i++) {
			stringList.add(subNode.get(i.toString(), defaults[i]));
		}
		return stringList;
	}

	private static List<String> getVariableSizedList(String key, String[] defaults) {
		List<String> stringList = new ArrayList<String>();
		Preferences subNode = getPrefs().node(key);
		try {
			for (String subKey : subNode.keys()) {
				stringList.add(subNode.get(subKey, ""));
			}
		} catch (BackingStoreException e) {
			System.out.println("Config Storage Backend not available:");
			e.printStackTrace();
		}
		if (stringList.isEmpty()) {

			stringList.addAll(Arrays.asList(defaults));
		}
		return stringList;
	}

	private static void setList(String key, List<String> list) {
		Preferences subNode = getPrefs().node(key);
		try {
			subNode.clear();
		} catch (BackingStoreException e) {
			System.out.println("Config Storage Backend not available:");
			e.printStackTrace();
		}
		for (Integer i = 0; i < list.size(); i++) {
			subNode.put(i.toString(), list.get(i));
		}
	}

	public static int getNumberOfPlayers() {
		return getPrefs().getInt("numberOfPlayers", 4);
	}

	public static void setNumberOfPlayers(int numberOfPlayers) {
		getPrefs().putInt("numberOfPlayers", numberOfPlayers);
	}

	public static List<String> getPlayerNames() {
		// String[] defaultNames = { "Joan", "Alex", "Donald", "Mary", "Peter",
		// "Julia" };
		String[] defaultNames = { "Joan", "Alex", "Donald", "Mary" };
		return getList("playerNames", defaultNames);
	}

	public static void setPlayerNames(List<String> playerNames) {
		setList("playerNames", playerNames);
	}

	public static List<Class<? extends IStrategy>> getPlayerStrategies() {
		List<Class<? extends IStrategy>> strategies = new ArrayList<Class<? extends IStrategy>>();
		// String[] defaultStrategies = { "HumanStrategy", "ChapelLab",
		// "ChapelLab", "ChapelLab", "ChapelLab", "ChapelLab" };
		String[] defaultStrategies = { "HumanStrategy", "ChapelLab", "ChapelLab", "ChapelLab" };

		for (String strategyName : getList("playerStrategies", defaultStrategies)) {
			strategies.add(getStrategyByName(strategyName));
		}

		return strategies;
	}

	private static Class<? extends IStrategy> getStrategyByName(String className) {
		final List<Class<? extends IStrategy>> allStrategies = ClassFinder.findStrategies();
		for (Class<? extends IStrategy> strategy : allStrategies) {
			if (strategy.getSimpleName().equals(className)) {
				return strategy;
			}
		}
		return ChapelLab.class;
	}

	public static void setPlayerStrategies(List<Class<? extends IStrategy>> playerStrategies) {
		List<String> strategyNames = new ArrayList<String>();
		for (Class<? extends IStrategy> strategy : playerStrategies) {
			strategyNames.add(strategy.getSimpleName());
		}
		setList("playerStrategies", strategyNames);
	}

	/**
	 * gets a list of cards that the user selected to be included in the supply
	 * 
	 * @return
	 */
	public static List<Class<? extends Card>> getSupplyCards() {
		List<Class<? extends Card>> supplyCards = new ArrayList<Class<? extends Card>>();
		for (String cardName : getVariableSizedList("supplyCards", new String[0])) {
			if (getCardByName(cardName) != null) {
				supplyCards.add(getCardByName(cardName));
			}
		}
		return supplyCards;
	}

	private static Class<? extends Card> getCardByName(String className) {
		final List<Class<? extends Card>> allCards = ClassFinder.findAllKingdomCards();
		for (Class<? extends Card> card : allCards) {
			if (card.getSimpleName().equals(className)) {
				return card;
			}
		}
		System.out.println("Couldn't find card " + className);
		return null;
	}

	public static void setSupplyCards(List<Class<? extends Card>> supplyCards) {
		List<String> cardNames = new ArrayList<String>();
		for (Class<? extends Card> cardClass : supplyCards) {
			cardNames.add(cardClass.getSimpleName());
		}
		setList("supplyCards", cardNames);
	}
}
