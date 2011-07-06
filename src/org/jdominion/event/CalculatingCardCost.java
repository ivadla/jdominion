package org.jdominion.event;

import org.jdominion.Card;

public class CalculatingCardCost extends Event {

	private int cardCost;

	public int getCurrentCost() {
		return cardCost;
	}

	public void decreaseCardCost(int decrement) {
		assert decrement > 0;
		if (decrement > cardCost) {
			cardCost = 0;
		} else {
			cardCost -= decrement;
		}
	}

	public CalculatingCardCost(Card card, int initialCost) {
		super("calculating cost of " + card.getName(), card.getOwner());
		cardCost = initialCost;
	}

	public static int calculateCost(Card card, int initialCost) {
		CalculatingCardCost event = new CalculatingCardCost(card, initialCost);
		EventManager.getInstance().handleEvent(event);
		return event.getCurrentCost();
	}
}
