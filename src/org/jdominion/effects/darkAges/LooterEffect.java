package org.jdominion.effects.darkAges;

import org.jdominion.Card.Type;
import org.jdominion.CardList;
import org.jdominion.CardPile;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.darkAges.RuinsPile;
import org.jdominion.effects.CardEffect;

/**
 * This is a dummy effect, which adds the ruins pile to the supply
 * 
 */
public class LooterEffect extends CardEffect {

	public LooterEffect() {
		super(Type.LOOTER);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		return true;
	}

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return 0;
	}

	@Override
	public int getCoins() {
		return 0;
	}
	
	@Override
	public void gameStarted(Game game) {
		game.getSupply().addCardPile(new RuinsPile(game));

	}

	protected static void giveRuins(Player playerWhoGainsRuins, Turn currentTurn, Supply supply) {

		for (CardPile pile : supply.getCardPiles()) {
			if (pile.isOfType(Type.RUINS)) {
				if (!pile.isEmpty()) {
					playerWhoGainsRuins.gainCard(pile.takeCard(), currentTurn, supply);
				}
				return;
			}
		}
	}

}
