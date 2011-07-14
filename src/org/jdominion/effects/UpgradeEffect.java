package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.CardPile;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;

public class UpgradeEffect extends TrashGainEffect {

	private int addedValue = 1;

	@Override
	protected ChooseCardsFromHandToTrash createDecision(Hand hand) {
		return new ChooseCardsFromHandToTrash(false, 1, 1, hand);
	}

	@Override
	protected Supply createSupply(Supply supply, Card cardToTrash) {
		List<CardPile> newPiles = new ArrayList<CardPile>();
		for (CardPile pile : supply.getCardPiles()) {
			if ((!pile.isEmpty()) && (pile.getCardCost() == cardToTrash.getCost() + addedValue)) {
				newPiles.add(pile);
			}
		}
		return new Supply(newPiles);
	}
}
