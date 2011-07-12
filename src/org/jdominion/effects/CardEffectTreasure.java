package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardEffectTreasure extends CardEffect {

	private final int coins;

	public CardEffectTreasure(int coins) {
		super(Card.Type.TREASURE);
		this.coins = coins;
	}

	@Override
	public int getCoins() {
		return this.coins;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		currentTurn.addExtraMoney(getCoins());
		return true;
	}

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return 0;
	}

}
