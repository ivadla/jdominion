package org.jdominion.aiStrategies;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.cards.common.Curse;
import org.jdominion.cards.common.Estate;
import org.jdominion.cards.seaside.Salvager;
import org.jdominion.decisions.ChooseActionCardToPlay;
import org.jdominion.effects.CardEffect;

public class OnlySalvagers extends BuyOneTypeOfActionCard {

	public OnlySalvagers() {
		this(1);
	}

	public OnlySalvagers(int numOfSalvagersToBuy) {
		super(Salvager.class, numOfSalvagersToBuy);
	}

	@Override
	public void decide(ChooseActionCardToPlay decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		if (hand.contains(Salvager.class)) {
			if (hand.contains(Curse.class) || hand.contains(Estate.class) || hand.contains(Copper.class)) {
				decision.addAnswer(hand.getCardByClass(Salvager.class));
			}
		} else {
			decision.setCanceled(true);
		}
	}

}
