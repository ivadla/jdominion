package org.jdominion.effects.darkAges;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.OnXEffect;
import org.jdominion.event.CardGained;

public class DeathCartOnGainEffect extends OnXEffect {

	public DeathCartOnGainEffect() {
		super(CardGained.class);
	}
	@Override
	protected void onX(Player player, Turn currentTurn, Supply supply) {
		LooterEffect.giveRuins(player, currentTurn, supply);
		LooterEffect.giveRuins(player, currentTurn, supply);
	}

}
