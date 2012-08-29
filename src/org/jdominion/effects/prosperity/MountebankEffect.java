package org.jdominion.effects.prosperity;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Copper;
import org.jdominion.cards.common.Curse;
import org.jdominion.decisions.prosperity.DiscardCurse;
import org.jdominion.effects.CardEffectSimpleAttack;

public class MountebankEffect extends CardEffectSimpleAttack {

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {
		if (playerToAttack.getHand().contains(Curse.class)) {
			DiscardCurse decision = new DiscardCurse();
			playerToAttack.decide(decision, this);
			if (decision.getAnswer()) {
				playerToAttack.discardCardsFromHand(new CardList(playerToAttack.getHand().getCardByClass(Curse.class)), currentTurn, supply);
				return;
			}
		}
		playerToAttack.gainCard(Curse.class, supply, currentTurn);
		playerToAttack.gainCard(Copper.class, supply, currentTurn);
	}

}
