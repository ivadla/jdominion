package org.jdominion.effects;

import org.jdominion.Card;

public abstract class CardEffectAttack extends CardEffectAction {

	public CardEffectAttack() {
		super(Card.Type.ATTACK);
	}
}
