package org.jdominion.effects;

import java.io.Serializable;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public abstract class CardEffect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Card.Type type;
	private Card card;

	public Card.Type getType() {
		return type;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public CardEffect(Card.Type type) {
		this.type = type;
	}

	public abstract boolean execute(Player activePlayer, Turn currentTurn, Supply supply);

	public abstract int getVictoryPoints(Player owner, List<Card> allCardsOfPlayer);

	public abstract int getCoins();

	/**
	 * @return the text for the Effect like it would appear on the card
	 */
	public String getText() {
		return "";
	}

	public int getAddedActions() {
		return 0;
	}

	public void isAddedToHand(Player owner) {
	}

	public void getsRemovedFromHand(Player owner) {
	}

	/**
	 * This is called once for every effect-class which is used in the game.
	 * 
	 * @param game
	 */
	public void gameStarted(Game game) {
	}

}
