package org.jdominion.effects.cornucopia;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Card.Type;

public class FarmingVillageEffect extends SearchForCardAndPutItInHand {

	public FarmingVillageEffect() {
		super(false);
	}

	@Override
	protected boolean isCorrectCard(Card card, Hand hand) {
		return card.isOfType(Type.ACTION) || card.isOfType(Type.TREASURE);
	}

}
