package org.jdominion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseActionCardToPlay;
import org.jdominion.decisions.ChooseCardToBuy;
import org.jdominion.decisions.ChooseTreasureCardToPlay;
import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.NullEffect;
import org.jdominion.event.EndOfTurn;
import org.jdominion.event.EventManager;
import org.jdominion.event.StartOfTurn;

public class Turn implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int turnNumber;
	private int availableActions = 1;
	private int extraMoney = 0;
	private int availableBuys = 1;
	private Player activePlayer;
	private Game game;

	private List<Card> playedCards = new ArrayList<Card>();

	public int getTurnNumber() {
		return turnNumber;
	}

	public void addActions(int value) {
		assert value > 0;
		this.availableActions += value;
	}

	public int getAvailableActions() {
		return availableActions;
	}

	public void addExtraMoney(int value) {
		assert value > 0;
		this.extraMoney += value;
	}

	public int getExtraMoney() {
		return extraMoney;
	}

	public void addBuys(int value) {
		assert value > 0;
		availableBuys += value;
	}

	public int getAvailableBuys() {
		return availableBuys;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	private void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public List<Card> getPlayedCards() {
		return playedCards;
	}

	public List<Card> getCardsInPlay() {
		// TODO: this should be different with duration cards
		return playedCards;
	}

	public Turn(Game game, Player activePlayer, int turnNumber) {
		this.setGame(game);
		this.setActivePlayer(activePlayer);
		this.turnNumber = turnNumber;
	}

	public void doTurn() {
		EventManager.getInstance().handleEvent(new StartOfTurn(this));
		playActionCards(activePlayer, game.getSupply());
		playTreasureCards(activePlayer, game.getSupply());
		buyCards(activePlayer, game.getSupply());
		cleanUp(activePlayer);
		EventManager.getInstance().handleEvent(new EndOfTurn(this));
	}

	public List<Player> getOtherPlayers() {
		List<Player> otherPlayers = new ArrayList<Player>(getGame().getPlayers());
		otherPlayers.remove(getActivePlayer());

		return otherPlayers;
	}

	public Player getPrevPlayer() {
		int indexOfPrevPlayer = game.getPlayers().indexOf(getActivePlayer()) - 1;
		if (indexOfPrevPlayer < 0) {
			indexOfPrevPlayer = game.getPlayers().size() - 1;
		}
		return game.getPlayers().get(indexOfPrevPlayer);
	}

	public Player getNextPlayer() {
		int indexOfNextPlayer = (game.getPlayers().indexOf(getActivePlayer()) + 1) % game.getPlayers().size();
		return game.getPlayers().get(indexOfNextPlayer);
	}

	private void playActionCards(Player activePlayer, Supply supply) {
		while (activePlayer.hasActionCardInHand() && (this.availableActions > 0)) {
			ChooseActionCardToPlay playDecision = new ChooseActionCardToPlay(activePlayer.getHand());
			activePlayer.decide(playDecision, new NullEffect());
			if (playDecision.isCanceled()) {
				return;
			}
			Card choosenCard = playDecision.getAnswer().get(0);
			assert choosenCard.isOfType(Card.Type.ACTION);
			this.availableActions--;
			this.playedCards.add(choosenCard);
			activePlayer.playCard(choosenCard, this, supply);
		}
	}

	private void playTreasureCards(Player activePlayer, Supply supply) {
		playBasicTreasureCards(activePlayer, supply);
		playOtherTreasureCards(activePlayer, supply);
	}

	private void playBasicTreasureCards(Player activePlayer, Supply supply) {
		for (Card cardInHand : new ArrayList<Card>(activePlayer.getHand().getCardList())) {
			if (isBasicTreasureCard(cardInHand)) {
				this.playedCards.add(cardInHand);
				activePlayer.playCard(cardInHand, this, supply);
			}
		}
	}

	private boolean isBasicTreasureCard(Card card) {
		return card.isOfType(Type.TREASURE) && card.getEffects().size() == 1 && card.getEffects().get(0).getClass() == CardEffectTreasure.class;
	}

	private void playOtherTreasureCards(Player activePlayer, Supply supply) {
		while (activePlayer.getHand().contains(Type.TREASURE)) {
			ChooseTreasureCardToPlay playDecision = new ChooseTreasureCardToPlay(activePlayer.getHand());
			activePlayer.decide(playDecision, new NullEffect());
			if (playDecision.isCanceled()) {
				return;
			}
			Card choosenCard = playDecision.getAnswer().get(0);
			assert choosenCard.isOfType(Card.Type.TREASURE);
			this.playedCards.add(choosenCard);
			activePlayer.playCard(choosenCard, this, supply);
		}
	}

	private void buyCards(Player activePlayer, Supply supply) {
		int availableCoins = this.extraMoney;
		while (this.availableBuys > 0) {

			ChooseCardToBuy buyDecision = new ChooseCardToBuy(supply.createSupplyWithMaximumCost(availableCoins), availableBuys, availableCoins);
			activePlayer.decide(buyDecision, new NullEffect());

			if (buyDecision.isCanceled()) {
				return;
			}

			Class<? extends Card> cardToBuy = buyDecision.getAnswer();

			int cost = supply.getCardCost(cardToBuy);

			assert cost <= availableCoins;
			assert supply.isCardAvailable(cardToBuy);

			activePlayer.buyCard(cardToBuy, this, supply);

			availableCoins -= cost;
			this.availableBuys--;
		}
	}

	private void cleanUp(Player activePlayer) {
		activePlayer.placeOnDiscardPile(playedCards);
		activePlayer.discardCardsFromHand(activePlayer.getHand().getCardList());
		activePlayer.drawNewHand();
	}

}
