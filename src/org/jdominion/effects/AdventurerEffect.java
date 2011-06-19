package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

//TODO: maybe generalize and rename it
public class AdventurerEffect extends CardEffectAction {

	private int treasureCardsToReveal = 2;

	public AdventurerEffect() {
		super();
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		List<Card> revealedTreasureCards = new ArrayList<Card>();
		List<Card> otherRevealedCards = new ArrayList<Card>();
		while (revealedTreasureCards.size() < treasureCardsToReveal) {
			List<Card> revealedCards = activePlayer.revealCards(1);
			if (revealedCards.size() == 1) {
				if (revealedCards.get(0).isOfType(Card.Type.TREASURE)) {
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
