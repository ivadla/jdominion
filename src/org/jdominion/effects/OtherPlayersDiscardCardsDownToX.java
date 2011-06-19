package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardFromHandToDiscard;

public class OtherPlayersDiscardCardsDownToX extends CardEffectSimpleAttack {

	private int numberOfCardsToKeep;

	public OtherPlayersDiscardCardsDownToX(int numberOfCardsToKeep) {
		super();
		this.numberOfCardsToKeep = numberOfCardsToKeep;
	}

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {
		while (playerToAttack.getHandSize() > numberOfCardsToKeep) {
			ChooseCardFromHandToDiscard decision = new ChooseCardFromHandToDiscard(false, playerToAttack.getHand());
			playerToAttack.decide(decision, this, playerToAttack.getHand(), currentTurn, supply);
			playerToAttack.discardCardsFromHand(decision.getAnswer());
		}		
	}

}
