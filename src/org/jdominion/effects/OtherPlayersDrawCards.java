package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.event.Attack;

public class OtherPlayersDrawCards extends CardEffectAction {

	private int numberOfCardsToDraw;
	private boolean isPartOfAnAttack = false;

	public OtherPlayersDrawCards(int numberOfCardsToDraw, boolean isPartOfAnAttack) {
		this(numberOfCardsToDraw);
		this.isPartOfAnAttack = isPartOfAnAttack;
	}

	public OtherPlayersDrawCards(int numberOfCardsToDraw) {
		super();
		this.numberOfCardsToDraw = numberOfCardsToDraw;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		for (Player player : currentTurn.getOtherPlayers()) {
			if (!isPartOfAnAttack || !Attack.isBlocked(player, activePlayer, this, currentTurn, supply)) {
				player.drawCardsIntoHand(this.numberOfCardsToDraw);
			}
		}
		return true;
	}
}
