package org.jdominion.effects.darkAges;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.DiscardRevealedCard;
import org.jdominion.decisions.revealedCards.Discard;
import org.jdominion.decisions.revealedCards.Option;
import org.jdominion.decisions.revealedCards.PutOnDeck;
import org.jdominion.decisions.revealedCards.RevealedCard;
import org.jdominion.effects.CardEffectAction;
import org.jdominion.effects.intrigue.IronworksEffect;

public class IronmongerEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		
		// TODO: refactor this code so it can be used by spy as well
		List<RevealedCard> revealedCards = new ArrayList<RevealedCard>();
		CardList cardList = activePlayer.revealCards(1);
		if (cardList.size() == 1) {
			List<Option> optionList = new ArrayList<Option>();
			optionList.add(PutOnDeck.getInstance());
			optionList.add(Discard.getInstance());
			revealedCards.add(new RevealedCard(cardList.getFirst(), activePlayer, optionList));
		} else {

			return false;
		}

		DiscardRevealedCard decision = new DiscardRevealedCard(revealedCards);
		activePlayer.decide(decision, this);

		RevealedCard revealedCard = decision.getAnswer().get(0);
		if (revealedCard.getChoosenOption() == PutOnDeck.getInstance()) {
			revealedCard.getOwner().placeOnDeck(revealedCard.getRevealedCard());
		} else {
			revealedCard.getOwner().placeOnDiscardPile(revealedCard.getRevealedCard());
		}

		// TODO: put it in a more central place
		IronworksEffect.giveBenefitBasedOnCardType(revealedCard.getRevealedCard().getClass(), activePlayer, currentTurn);

		return true;
	}

}
