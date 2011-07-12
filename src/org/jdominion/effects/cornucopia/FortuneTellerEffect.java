package org.jdominion.effects.cornucopia;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.effects.CardEffectSimpleAttack;

public class FortuneTellerEffect extends CardEffectSimpleAttack {

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {

		CardList setAsideCards = new CardList();
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
