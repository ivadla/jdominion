package org.jdominion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jdominion.aiStrategies.IStrategy;
import org.jdominion.cards.Province;
import org.jdominion.effects.CardEffect;

public class Game implements Serializable, ICurrentTurn {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int NUMBER_OF_CARDS_IN_HAND = 5;

	private List<Player> players;
	private Supply supply;
	private List<Player> winners = null;
	private int turnCounter = 0;
	private Turn currentTurn = null;
	private List<Card> trash;

	private void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Supply getSupply() {
		return supply;
	}

	private void setWinners(List<Player> winners) {
		this.winners = winners;
	}

	public List<Player> getWinners() {
		return winners;
	}

	private void setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
	}

	public int getTurnCounter() {
		return turnCounter;
	}

	public Turn getCurrentTurn() {
		return currentTurn;
	}

	public void addCardsToTrash(List<Card> cardsToTrash) {
		this.trash.addAll(cardsToTrash);
	}
	
	public void removeCardFromTrash(Card cardToRemove) {
		this.trash.remove(cardToRemove);
	}

	public List<Card> getTrash() {
		return trash;
	}

	public Game(List<Player> players, Supply supply) {
		this.setPlayers(players);
		this.supply = supply;
		this.trash = new ArrayList<Card>();
	}
	
	public Game(List<String> playerNames, List<Class<? extends IStrategy>> playerStrategies, List<Class<? extends Card>> kingdomCardsToAddToSupply) {
		assert playerNames.size() > 0 : "The game needs players";
		assert playerNames.size() == playerStrategies.size(): "There should be one strategy for each player";
		this.players = new ArrayList<Player>();
		for(int i=0; i<playerNames.size();i++) {
			try {
				this.players.add(new Player(playerNames.get(i),CardFactory.createInitialDeck() , playerStrategies.get(i).newInstance() ));
			} catch (Exception e) {
				throw new RuntimeException("Error while creating player " + playerNames.get(i), e);
			}
		}
		Collections.shuffle(this.players);
		
		this.supply = CardFactory.createSupply(players.size(),kingdomCardsToAddToSupply);
		this.trash = new ArrayList<Card>();
	}

	private boolean gameIsRunning() {
		return getWinners() == null;
	}

	public boolean isVictoryConditionReached() {
		return !supply.isCardAvailable(Province.class) || (supply.countEmptyPiles() >= 3);
	}

	private List<Player> findWinners() {
		List<Player> winners = null;
		int highestPoints = Integer.MIN_VALUE;

		for (Player player : getPlayers()) {
			if (player.countVictoryPoints(null) > highestPoints) {
				winners = new ArrayList<Player>();
				winners.add(player);
				highestPoints = player.countVictoryPoints(null);
			} else if (player.countVictoryPoints(null) == highestPoints) {
				if (player.getTurnCounter() < winners.get(0).getTurnCounter()) {
					winners = new ArrayList<Player>();
					winners.add(player);
				} else if (player.getTurnCounter() == winners.get(0).getTurnCounter()) {
					winners.add(player);
				}
			}
		}
		return winners;
	}

	public void runGame() {
		informPlayersAboutGameStart();
		informEffectsAboutGameStart();
		while (gameIsRunning()) {
			setTurnCounter(getTurnCounter() + 1);
			for (Player activePlayer : getPlayers()) {
				activePlayer.incrementTurnCounter();
				currentTurn = new Turn(this, activePlayer, getTurnCounter());
				currentTurn.doTurn();
				if (isVictoryConditionReached()) {
					setWinners(findWinners());
					informPlayersAboutGameEnd();
					return;
				}
			}
		}
	}
	
	private void informPlayersAboutGameStart() {
		for (Player player : players) {
			player.gameStarted(this);
		}
	}

	private void informEffectsAboutGameStart() {
		// make a copy to prevent concurrent modifications
		List<CardPile> piles = new ArrayList<CardPile>(supply.getCardPiles());

		List<Class<? extends CardEffect>> informedEffects = new ArrayList<Class<? extends CardEffect>>();

		for (CardPile pile : piles) {
			for (CardEffect effect : pile.getCardEffects()) {
				if (!informedEffects.contains(effect.getClass())) {
					effect.gameStarted(this);
					informedEffects.add(effect.getClass());
				}
			}
		}
	}

	private void informPlayersAboutGameEnd() {
		for (Player player : players) {
			player.gameEnded(getWinners(), players);
		}
	}
}
