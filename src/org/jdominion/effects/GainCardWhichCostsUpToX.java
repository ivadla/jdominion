package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.CardPile;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.location.DiscardPile;
import org.jdominion.location.Location;

public class GainCardWhichCostsUpToX extends CardEffectGainCard {


	private int maximumCost;
	private Location whereToPutCard = new DiscardPile();

	public GainCardWhichCostsUpToX(int maximumCost) {
		super();
		this.maximumCost = maximumCost;
	}

	public GainCardWhichCostsUpToX(int maximumCost, Location whereToPutCard) {
		this(maximumCost);
		this.whereToPutCard = whereToPutCard;
	}

	@Override
	protected boolean isCardPilePermitted(CardPile pile) {
		return pile.getCardCost() <= maximumCost;
	}

	@Override
	protected void gainCard(Class<? extends Card> choosenCard, Player activePlayer, Turn currentTurn, Supply supply) {
		activePlayer.gainCard(choosenCard, supply, whereToPutCard, currentTurn);
	}

}
