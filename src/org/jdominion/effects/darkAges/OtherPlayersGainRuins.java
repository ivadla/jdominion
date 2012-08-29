package org.jdominion.effects.darkAges;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectSimpleAttack;

public class OtherPlayersGainRuins extends CardEffectSimpleAttack {

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {
		LooterEffect.giveRuins(playerToAttack, currentTurn, supply);
	}

}
