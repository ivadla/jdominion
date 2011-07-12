package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardEffectCurse extends CardEffect {

	private int victoryPoints;

	public CardEffectCurse(int victoryPoints) {
		super(Card.Type.CURSE);
		assert victoryPoints < 0 : "Curses have negative Points";
		this.victoryPoints = victoryPoints;
	}

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return victoryPoints;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		return false;
	}

	@Override
	public int getCoins() {
		return 0;
	}
}
