package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.event.Attack;

public abstract class CardEffectSimpleAttack extends CardEffectAttack {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		for (Player player : currentTurn.getOtherPlayers()) {
			if (!Attack.isBlocked(player, activePlayer, this, currentTurn, supply)) {
				attackPlayer(player, currentTurn, supply);
			}
		}
		return true;
	}

	protected abstract void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply);

}
