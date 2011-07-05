package org.jdominion.effects.base;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.base.ChooseVictoryCardFromHandToPutOnDeck;
import org.jdominion.effects.CardEffectSimpleAttack;

public class OtherPlayersPutVictoryCardBackOnDeck extends CardEffectSimpleAttack {

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {
		if (playerToAttack.getHand().contains(Type.VICTORY)) {
			ChooseVictoryCardFromHandToPutOnDeck decision = new ChooseVictoryCardFromHandToPutOnDeck(playerToAttack
					.getHand());
			playerToAttack.decide(decision, this);
			assert decision.getAnswer().size() == 1;
			playerToAttack.placeOnDeck(decision.getAnswer().get(0));
		} else {
			playerToAttack.revealHand();
		}
	}

}
