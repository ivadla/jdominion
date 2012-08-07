package org.jdominion.effects.promo;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.promo.ChooseCardToDiscardFromRevealedCards;
import org.jdominion.decisions.revealedCards.Discard;
import org.jdominion.decisions.revealedCards.RevealedCard;
import org.jdominion.effects.CardEffectAction;

public class EnvoyEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		CardList revealedCards = activePlayer.revealCards(5);

		if (revealedCards.isEmpty()) {
			return false;
		}
		
		ChooseCardToDiscardFromRevealedCards decision = new ChooseCardToDiscardFromRevealedCards(revealedCards);
		currentTurn.getNextPlayer().decide(decision, this);
		for (RevealedCard revealedCard : decision.getAnswer()) {
			if (revealedCard.getChoosenOption() == Discard.getInstance()) {
				activePlayer.placeOnDiscardPile(revealedCard.getRevealedCard());
			} else {
				activePlayer.addCardToHand(revealedCard.getRevealedCard());
			}
		}
		return true;
	}
}
