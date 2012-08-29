package org.jdominion.effects.darkAges;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.cards.common.Silver;
import org.jdominion.effects.CardEffectReaction;
import org.jdominion.event.CardEvent;
import org.jdominion.event.Event;
import org.jdominion.location.TopOfDeck;

public class BeggarReactionEffect extends CardEffectReaction {

	@Override
	public void handleReaction(Event event, Player reactingPlayer) {
		assert event instanceof CardEvent;
		CardEvent cardEvent = (CardEvent) event;
		if (reactingPlayer.getHand().contains(getCard())) {
			reactingPlayer.discardCardsFromHand(new CardList(this.getCard()), cardEvent.getCurrentTurn(), cardEvent.getSupply());
			reactingPlayer.gainCard(Silver.class, cardEvent.getSupply(), new TopOfDeck(), cardEvent.getCurrentTurn());
			reactingPlayer.gainCard(Silver.class, cardEvent.getSupply(), cardEvent.getCurrentTurn());
		}
	}

}
