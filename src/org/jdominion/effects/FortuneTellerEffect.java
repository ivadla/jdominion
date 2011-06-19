package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;

public class FortuneTellerEffect extends CardEffectSimpleAttack {

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {

		List<Card> setAsideCards = new ArrayList<Card>();
		while ((playerToAttack.getDeckSize() + playerToAttack.getDiscardPileSize()) > 0) {
			Card revealedCard = playerToAttack.revealCard();
			if (revealedCard.isOfType(Type.VICTORY) || revealedCard.isOfType(Type.CURSE)) {
				playerToAttack.placeOnDeck(revealedCard);
				break;
			} else {
				setAsideCards.add(revealedCard);
			}
		}

		playerToAttack.placeOnDiscardPile(setAsideCards);
	}
}
