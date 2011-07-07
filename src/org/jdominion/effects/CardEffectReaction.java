package org.jdominion.effects;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.event.Event;
import org.jdominion.event.ReactionEventHandler;

public abstract class CardEffectReaction extends CardEffectAction {

	private Player owner;

	public CardEffectReaction() {
		super(Type.REACTION);
	}

	protected Player getOwner() {
		return owner;
	}

	public abstract void handleReaction(Event event, Player reactingPlayer);

	@Override
	public void getsRemovedFromHand(Player owner) {
		ReactionEventHandler.getInstance().removeReactionCard(getCard(), owner);
	}

	@Override
	public void isAddedToHand(Player owner) {
		this.owner = owner;
		ReactionEventHandler.getInstance().addReactionCard(getCard(), owner);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		// reaction effects should not execute anything
		// what happens during normal play of the card belongs in a different
		// Effect-class
		return false;
	}

}
