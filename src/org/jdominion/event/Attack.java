package org.jdominion.event;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffect;

public class Attack extends Event {

	private Player attackedPlayer;
	private Player attacker;
	private CardEffect effect; // TODO: rename it to cause or something like
								// that

	public Player getAttackedPlayer() {
		return attackedPlayer;
	}

	public Player getAttacker() {
		return attacker;
	}

	public Attack(Player attackedPlayer, Player attacker, CardEffect effect) {
		super(attacker.getName() + " attacks " + attackedPlayer.getName() + " with " + effect.getCard().getName(), attackedPlayer, true);
		this.attackedPlayer = attackedPlayer;
		this.attacker = attacker;
		this.effect = effect;
	}

	public static boolean isBlocked(Player attackedPlayer, Player attacker, CardEffect effect, Turn currentTurn, Supply supply) {
		Attack event = new Attack(attackedPlayer, attacker, effect);
		EventManager.getInstance().handleEvent(event);
		return event.isCanceled();
	}

	public CardEffect getEffect() {
		return effect;
	}

}
