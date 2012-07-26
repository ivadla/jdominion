package org.jdominion.event;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardGained extends Event {

	private Card gainedCard;
	private Turn currentTurn;
	private Supply supply;

	public Card getGainedCard() {
		return gainedCard;
	}

	public Turn getCurrentTurn() {
		return currentTurn;
	}

	public Supply getSupply() {
		return supply;
	}

	public CardGained(Player gainingPlayer, Card gainedCard, Turn currentTurn, Supply supply) {
		super(gainingPlayer.getName() + " gains " + gainedCard.getName() + ".", gainingPlayer);
		this.gainedCard = gainedCard;
		this.currentTurn = currentTurn;
		this.supply = supply;
	}

}
