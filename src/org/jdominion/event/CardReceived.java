package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public abstract class CardReceived extends Event {


	protected Card receivedCard;
	protected Turn currentTurn;
	protected Supply supply;

	public CardReceived(String verb, Player affectedPlayer, Card receivedCard, Turn currentTurn, Supply supply) {
		super(affectedPlayer.getName() + " " + verb + " " + receivedCard.getName() + ".", affectedPlayer);
		this.receivedCard = receivedCard;
		this.currentTurn = currentTurn;
		this.supply = supply;
	}

	public Card getReceivedCard() {
		return receivedCard;
	}

	public Turn getCurrentTurn() {
		return currentTurn;
	}

	public Supply getSupply() {
		return supply;
	}

}