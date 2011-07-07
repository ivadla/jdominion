package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.location.DiscardPile;
import org.jdominion.location.Location;

public class GainCardX extends CardEffectAction {

	private Class<? extends Card> cardToGain;
	private Location whereToPlaceCard;

	public GainCardX(Class<? extends Card> cardToGain) {
		this(cardToGain, new DiscardPile());
	}

	public GainCardX(Class<? extends Card> cardToGain, Location whereToPlaceCard) {
		this.cardToGain = cardToGain;
		this.whereToPlaceCard = whereToPlaceCard;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (supply.isCardAvailable(cardToGain)) {
			activePlayer.gainCard(cardToGain, supply, whereToPlaceCard);
			return true;
		} else {
			return false;
		}
	}

}
