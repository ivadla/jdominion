package org.jdominion.effects.prosperity;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.effects.OnXEffect;
import org.jdominion.event.CardBought;

public class MintOnBuyEffect extends OnXEffect {

	public MintOnBuyEffect() {
		super(CardBought.class);
	}

	@Override
	protected void onX(Player gainingPlayer, Turn currentTurn, Supply supply) {
		gainingPlayer.trashCards(gainingPlayer.getCardsInPlay().getCardsOfType(Type.TREASURE), currentTurn.getGame());

	}

}
