package org.jdominion.event;

import org.jdominion.Card;

/**
 * This event is called when a basic treasure card is played so that other cards can manipulate the value of the
 * treasure
 * 
 */
public class CalculateValue extends Event {

	private Card card;
	private int currentValue;

	public Card getCard() {
		return card;
	}

	public int getCurentValue() {
		return currentValue;
	}

	public void increaseValue(int increment) {
		currentValue += increment;
	}

	public CalculateValue(Card card, int initialValue) {
		super("Calculate the coins the card " + card.getName() + " costs.", card.getOwner());
		this.card = card;
		this.currentValue = initialValue;
	}

	public static int calculateValue(Card card, int initialValue) {
		CalculateValue event = new CalculateValue(card, initialValue);
		EventManager.getInstance().handleEvent(event);
		return event.getCurentValue();
	}
}
