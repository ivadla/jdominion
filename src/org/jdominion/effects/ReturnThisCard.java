package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.CardPile;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.extraGameData.ExtraGameData;
import org.jdominion.extraGameData.ExtraGameDataCardPile;

public class ReturnThisCard extends CardEffect {

	public ReturnThisCard() {
		super(null);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (activePlayer.getCardsInPlay().contains(this.getCard())) {
			activePlayer.getCardsInPlay().remove(this.getCard());
			putBack(getCard(), supply, currentTurn.getGame());
			return true;
		} else {
			return false;
		}
	}

	private void putBack(Card card, Supply supply, Game game) {
		if (supply.putCard(this.getCard())) {
			return;
		}
		for (ExtraGameData<?> extraGameData : game.getAllExtraGameData()) {
			if (extraGameData instanceof ExtraGameDataCardPile) {
				CardPile pile = ((ExtraGameDataCardPile) extraGameData).get();
				if (pile.getCardClass().equals(card.getClass())) {
					pile.putCard(card);
					return;
				}
			}
		}
	}

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return 0;
	}

	@Override
	public int getCoins() {
		return 0;
	}

}
