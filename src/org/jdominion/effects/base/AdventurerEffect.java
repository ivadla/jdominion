package org.jdominion.effects.base;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectAction;

//TODO: maybe generalize and rename it
public class AdventurerEffect extends CardEffectAction {

	private int treasureCardsToReveal = 2;

	public AdventurerEffect() {
		super();
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		CardList revealedTreasureCards = new CardList();
		CardList otherRevealedCards = new CardList();
		while (revealedTreasureCards.size() < treasureCardsToReveal) {
			CardList revealedCards = activePlayer.revealCards(1);
			if (revealedCards.size() == 1) {
				if (revealedCards.getFirst().isOfType(Card.Type.TREASURE)) {
					revealedTreasureCards.addAll(revealedCards);
				} else {
					otherRevealedCards.addAll(revealedCards);
				}
			} else { // there are no cards left to draw
				break;
			}
		}

		activePlayer.addCardsToHand(revealedTreasureCards);
		activePlayer.placeOnDiscardPile(otherRevealedCards);

		return revealedTreasureCards.size() == treasureCardsToReveal;

	}

}
