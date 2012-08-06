package org.jdominion.effects.promo;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.promo.ChooseCardToDiscardFromRevealedCards;
import org.jdominion.decisions.revealedCards.Discard;
import org.jdominion.decisions.revealedCards.Option;
import org.jdominion.decisions.revealedCards.PutInHand;
import org.jdominion.decisions.revealedCards.RevealedCard;
import org.jdominion.effects.CardEffectAction;

public class EnvoyEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		List<RevealedCard> revealedCards = new ArrayList<RevealedCard>();
		for(Card revealedCard: activePlayer.revealCards(5)) {
			revealedCards.add(new RevealedCard(revealedCard, activePlayer, getPossibleOptions()));
		}
		
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

	private List<Option> getPossibleOptions() {
		List<Option> optionList = new ArrayList<Option>();
		optionList.add(PutInHand.getInstance());
		optionList.add(Discard.getInstance());
		return optionList;
	}

}
