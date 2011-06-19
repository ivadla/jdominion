package org.jdominion.decisions;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.event.Event;

public class ChooseReactionCardToUse extends ChooseCardsFromHand {
	
	private Event event;

	public ChooseReactionCardToUse(Hand hand, Event event) {
		super("Choose a reaction card to counter " + event.getDescription(), true, 1, 1, hand);
		this.event = event;
	}

	public Event getEvent() {
		return event;
	}

	@Override
	protected boolean isValidCard(Card card) {
		// TODO: not all reaction cards can react to attacks
		return card.isOfType(Type.REACTION);
	}

	// should be unused, because we override chooseDefaultAnswer
	@Override
	protected Card chooseCardForDefaultAnswer(Hand copyOfHand) {
		return null;
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		//TODO: we need to choose a card here, but first we have to make sure 
		// that we don't run into an endless loop if we choose a card here.
		setCanceled(true);
	}

}
