package org.jdominion.effects.prosperity;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.effects.CardEffectTreasure;

public class BankEffect extends CardEffectTreasure {

	public BankEffect() {
		// for basic strategic evaluation we assume that the card provides one coin
		super(1);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		// playedCards already contains this Bank at this stage
		currentTurn.addExtraMoney(currentTurn.getPlayedCards().countCardsOfType(Type.TREASURE));
		return true;
	}

}
