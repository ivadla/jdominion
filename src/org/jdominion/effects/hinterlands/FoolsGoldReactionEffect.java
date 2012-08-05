package org.jdominion.effects.hinterlands;

import org.jdominion.Player;
import org.jdominion.cards.common.Gold;
import org.jdominion.cards.common.Province;
import org.jdominion.decisions.hinterlands.TrashFoolsGoldDecision;
import org.jdominion.effects.CardEffectReaction;
import org.jdominion.event.CardGained;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.IEventHandler;
import org.jdominion.event.EventManager.Duration;
import org.jdominion.location.TopOfDeck;

public class FoolsGoldReactionEffect extends CardEffectReaction implements IEventHandler {

	@Override
	public void isAddedToHand(Player owner) {
		EventManager.getInstance().addEventHandler(this, CardGained.class, Duration.FOREVER);
	}

	@Override
	public void getsRemovedFromHand(Player owner) {
		EventManager.getInstance().removeEventHandler(this, CardGained.class);
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof CardGained) {
			CardGained cardGainedEvent = (CardGained) event;
			Player owner = getCard().getOwner();
			if (cardGainedEvent.getReceivedCard().getClass().equals(Province.class) && !cardGainedEvent.getAffectedPlayer().equals(owner)) {
				TrashFoolsGoldDecision decision = new TrashFoolsGoldDecision();
				owner.decide(decision, this);
				if (decision.getAnswer() == true) {
					owner.trashCard(getCard(), cardGainedEvent.getCurrentTurn().getGame());
					owner.gainCard(Gold.class, cardGainedEvent.getSupply(), new TopOfDeck(), cardGainedEvent.getCurrentTurn());
				}

			}
		}

	}

	@Override
	public void handleReaction(Event event, Player reactingPlayer) {
		// not used here, because this is not a normal reaction card
	}

}
