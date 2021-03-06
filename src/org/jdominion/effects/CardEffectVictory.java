package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class CardEffectVictory extends CardEffect {

	private int victoryPoints;

	protected CardEffectVictory() {
		super(Card.Type.VICTORY);
	}

	public CardEffectVictory(int victoryPoints) {
		this();
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
