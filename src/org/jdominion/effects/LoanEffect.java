package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.TrashRevealedCard;

public class LoanEffect extends CardEffectTreasure {

	public LoanEffect() {
		super(0);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		List<Card> allRevealedCards = new ArrayList<Card>();
		while ((activePlayer.getDeckSize() + activePlayer.getDiscardPileSize()) > 0) {

			List<Card> revealedCardList = activePlayer.revealCards(1);
			assert revealedCardList.size() == 1;
			Card revealedCard = revealedCardList.get(0);

			if (revealedCard.isOfType(Type.TREASURE)) {
				TrashRevealedCard decison = new TrashRevealedCard(revealedCard);
				activePlayer.decide(decison, this, activePlayer.getHand(), currentTurn, supply);
				if (decison.getAnswer()) {
					activePlayer.trashCard(revealedCard, currentTurn.getGame());
				} else {
					allRevealedCards.add(revealedCard);
				}
				activePlayer.placeOnDiscardPile(allRevealedCards);
				return true;
			} else {
				allRevealedCards.add(revealedCard);
			}
		}
		activePlayer.placeOnDiscardPile(allRevealedCards);
		return false;
	}

}
