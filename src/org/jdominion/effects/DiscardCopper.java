package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;

public class DiscardCopper extends CardEffectSimpleAttack {

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {
		if (playerToAttack.getHand().contains(Copper.class)) {
			List<Card> cardsToDiscard = new ArrayList<Card>();
			cardsToDiscard.add(playerToAttack.getHand().getCardByClass(Copper.class));
			playerToAttack.discardCardsFromHand(cardsToDiscard);
		}
	}

}
