package org.jdominion.aiStrategies;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Util;
import org.jdominion.cards.Moat;
import org.jdominion.decisions.ChooseReactionCardToUse;
import org.jdominion.effects.CardEffect;
import org.jdominion.event.Event;

public class MoatStrategy extends BuyOneTypeOfActionCard {

	private Event lastEvent;

	public MoatStrategy() {
		super(Moat.class, 2);
	}

	public void decide(ChooseReactionCardToUse decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		// prevent an endless loop in showing the reaction card
		if (decision.getEvent() == lastEvent) {
			decision.setCanceled(true);
		} else {
			if (hand.contains(Moat.class)) {
				decision.setAnswer(Util.createCardList(hand.getCardByClass(Moat.class)));
			}
			lastEvent = decision.getEvent();
		}
	}

}
