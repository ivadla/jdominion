package org.jdominion.effects.base;

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
import org.jdominion.effects.CardEffectAttack;
import org.jdominion.event.Attack;

public class DiscardTopCard extends CardEffectAttack {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		List<RevealedCard> revealedCards = new ArrayList<RevealedCard>();
		for (Player player : currentTurn.getGame().getPlayers()) {
			if ((player == activePlayer) || !Attack.isBlocked(player, activePlayer, this, currentTurn, supply)) {
				CardList cardList = player.revealCards(1);
				if (cardList.size() == 1) {
					List<Option> optionList = new ArrayList<Option>();
					optionList.add(PutOnDeck.getInstance());
					optionList.add(Discard.getInstance());
					revealedCards.add(new RevealedCard(cardList.getFirst(), player, optionList));
				}
			}
		}
		if (revealedCards.isEmpty()) {

			return false;
		}

		DiscardRevealedCard decision = new DiscardRevealedCard(revealedCards);
		activePlayer.decide(decision, this);

		for (RevealedCard revealedCard : decision.getAnswer()) {
			if (revealedCard.getChoosenOption() == PutOnDeck.getInstance()) {
				revealedCard.getOwner().placeOnDeck(revealedCard.getRevealedCard());
			} else {
				revealedCard.getOwner().placeOnDiscardPile(revealedCard.getRevealedCard());
			}
		}

		return true;
	}

}
