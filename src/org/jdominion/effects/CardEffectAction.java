package org.jdominion.effects;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;

public abstract class CardEffectAction extends CardEffect {

	public CardEffectAction() {
		super(Card.Type.ACTION);
	}

	protected CardEffectAction(Card.Type type) {
		super(type);
	}

	@Override
	public int getCoins() {
		return 0;
	}

	@Override
	public int getVictoryPoints(Player owner, List<Card> allCardsOfPlayer) {
		return 0;
	}

}
