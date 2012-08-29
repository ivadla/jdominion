package org.jdominion.effects.seaside;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.effects.CardEffectSimpleAttack;

public class DiscardCopper extends CardEffectSimpleAttack {

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {
		if (playerToAttack.getHand().contains(Copper.class)) {
			CardList cardsToDiscard = new CardList();
			cardsToDiscard.add(playerToAttack.getHand().getCardByClass(Copper.class));
			playerToAttack.discardCardsFromHand(cardsToDiscard, currentTurn, supply);
		}
	}

}
