package org.jdominion.effects.prosperity;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.prosperity.TrashRevealedCard;
import org.jdominion.effects.CardEffectTreasure;

public class LoanEffect extends CardEffectTreasure {

	public LoanEffect() {
		super(0);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		CardList allRevealedCards = new CardList();
		while ((activePlayer.getDeckSize() + activePlayer.getDiscardPileSize()) > 0) {

			CardList revealedCardList = activePlayer.revealCards(1);
			assert revealedCardList.size() == 1;
			Card revealedCard = revealedCardList.getFirst();

			if (revealedCard.isOfType(Type.TREASURE)) {
				TrashRevealedCard decison = new TrashRevealedCard(revealedCard);
				activePlayer.decide(decison, this);
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
