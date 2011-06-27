package org.jdominion.effects.base;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Util;
import org.jdominion.Card.Type;
import org.jdominion.decisions.StealFromRevealedCards;
import org.jdominion.decisions.revealedCards.Discard;
import org.jdominion.decisions.revealedCards.Gain;
import org.jdominion.decisions.revealedCards.Option;
import org.jdominion.decisions.revealedCards.RevealedCard;
import org.jdominion.decisions.revealedCards.Trash;
import org.jdominion.effects.CardEffectAttack;
import org.jdominion.event.Attack;

public class StealTreasureCards extends CardEffectAttack {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		List<RevealedCard> revealedCards = new ArrayList<RevealedCard>();
		for (Player player : currentTurn.getOtherPlayers()) {
			if (!Attack.isBlocked(player, activePlayer, this, currentTurn, supply)) {
				List<Card> cardList = player.revealCards(2);
				for (Card revealedCard : cardList) {
					List<Option> optionList = new ArrayList<Option>();
					if(revealedCard.isOfType(Type.TREASURE)) {
						optionList.add(Gain.getInstance());
						optionList.add(Trash.getInstance());
						if(Util.countCardsOfType(cardList, Type.TREASURE) > 1) {
							optionList.add(Discard.getInstance());
						}
					} else {
						optionList.add(Discard.getInstance());
					}
					revealedCards.add(new RevealedCard(revealedCard, player, optionList));
				}
			}
		}
		if (revealedCards.size() == 0) {

			return false;
		}
		
		StealFromRevealedCards decision = new StealFromRevealedCards(revealedCards);
		
		activePlayer.decide(decision, this, activePlayer.getHand(), currentTurn, supply);
		
		for (RevealedCard revealedCard : decision.getAnswer()) {
			if(revealedCard.getChoosenOption() == Discard.getInstance()) {
				revealedCard.getOwner().placeOnDiscardPile(revealedCard.getRevealedCard());
			} else {
				revealedCard.getOwner().trashCard(revealedCard.getRevealedCard(), currentTurn.getGame());
				if(revealedCard.getChoosenOption() == Gain.getInstance()) {
					currentTurn.getGame().removeCardFromTrash(revealedCard.getRevealedCard());
					activePlayer.gainCard(revealedCard.getRevealedCard());
				}	
			}
			
		}
		return true;
	}

}
