package org.jdominion.effects.intrigue;

import org.jdominion.Card;
import org.jdominion.CardClassInfo;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.effects.GainCardWhichCostsUpToX;

public class IronworksEffect extends GainCardWhichCostsUpToX {

	public IronworksEffect() {
		super(4);
	}

	@Override
	protected void gainCard(Class<? extends Card> choosenCard, Player activePlayer, Turn currentTurn, Supply supply) {
		super.gainCard(choosenCard, activePlayer, currentTurn, supply);
		for (Type type : CardClassInfo.getInstance().getTypes(choosenCard)) {
			switch (type) {
			case ACTION:
				currentTurn.addActions(1);
				break;
			case TREASURE:
				currentTurn.addExtraMoney(1);
				break;
			case VICTORY:
				activePlayer.drawCardsIntoHand(1);
				break;
			}
		}
	}
}
