package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class GainCardX extends CardEffectAction {

	private Class<? extends Card> cardToGain;

	public GainCardX(Class<? extends Card> cardToGain) {
		super();
		this.cardToGain = cardToGain;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (supply.isCardAvailable(cardToGain)) {
			activePlayer.gainCard(cardToGain, supply);
			return true;
		} else {
			return false;
		}
	}

}
