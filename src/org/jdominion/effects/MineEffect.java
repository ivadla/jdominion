package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.CardPile;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;
import org.jdominion.decisions.ChooseTreasureCardFromHandToTrash;
import org.jdominion.location.Location;

public class MineEffect extends TrashGainEffect {

	private int addedValue = 3;

	public MineEffect(int addedValue) {
		super();
		this.addedValue = addedValue;
	}

	@Override
	protected ChooseCardsFromHandToTrash createDecision(Hand hand) {
		return new ChooseTreasureCardFromHandToTrash(false, hand);
	}

	@Override
	protected Supply createSupply(Supply supply, Card cardToTrash) {
		List<CardPile> newPiles = new ArrayList<CardPile>();
		for (CardPile pile : supply.getCardPiles()) {
			if ((pile.getNumberOfCardsInPile() > 0) && (pile.isOfType(Type.TREASURE))
					&& (pile.getCardCost() <= cardToTrash.getCost() + addedValue)) {
				newPiles.add(pile);
			}
		}
		return new Supply(newPiles);
	}

	@Override
	protected Location whereToPutCard() {
		return new org.jdominion.location.Hand();
	}

}
