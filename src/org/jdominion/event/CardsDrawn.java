package org.jdominion.event;

import org.jdominion.Player;

//TODO: make this a CardEvent
public class CardsDrawn extends Event {

	private int numberOfDrawnCards;

	public int getNumberOfDrawnCards() {
		return numberOfDrawnCards;
	}

	public CardsDrawn(Player drawingPlayer, int numberOfDrawnCards) {
		super(drawingPlayer.getName() + " draws " + numberOfDrawnCards + " cards.", drawingPlayer);
		this.numberOfDrawnCards = numberOfDrawnCards;
	}

}
