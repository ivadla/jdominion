package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

/**
 * This event happens if something is done with a card. E.g. the card is played, trashed, gained, ...
 * 
 */
public abstract class CardEvent extends Event {


	protected Card card;
	protected Turn currentTurn;
	protected Supply supply;

	public CardEvent(Player affectedPlayer, Card card, Turn currentTurn, Supply supply, String message) {
		super(message, affectedPlayer);
		this.card = card;
		this.currentTurn = currentTurn;
		this.supply = supply;

	}

	public CardEvent(String verb, Player affectedPlayer, Card card, Turn currentTurn, Supply supply) {
		this(affectedPlayer, card, currentTurn, supply, affectedPlayer.getName() + " " + verb + " " + card.getName() + ".");
	}

	public Card getCard() {
		return card;
	}

	public Turn getCurrentTurn() {
		return currentTurn;
	}

	public Supply getSupply() {
		return supply;
	}

}